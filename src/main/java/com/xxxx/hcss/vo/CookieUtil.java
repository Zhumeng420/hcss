package com.xxxx.hcss.vo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

public final class CookieUtil {

    public static String getCookieValue(HttpServletRequest request,String cookieName){
        return getCookieValue(request,cookieName,false);
    }

    public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecoder){
        Cookie[] cookieList=request.getCookies();
        if(cookieList==null||cookieName==null){
            return null;
        }
        String retValue=null;
        try{
            for(int i=0;i<cookieList.length;i++){
                if(cookieList[i].getName().equals(cookieName)){
                    if(isDecoder){
                        retValue= URLDecoder.decode(cookieList[i].getValue(),"UTF-8");
                    }else{
                        retValue=cookieList[i].getValue();
                    }
                }
                break;
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return retValue;
    }

    public static String getCookieValue(HttpServletRequest request,String cookieName,String encodeString){
        Cookie[] cookiesList=request.getCookies();
        if(cookiesList==null||cookieName==null){
            return null;
        }
        String retValue=null;
        try{
            for (int i=0;i< cookiesList.length;i++){
                if(cookiesList[i].getName().equals(cookieName)){
                    retValue=URLDecoder.decode(cookiesList[i].getValue(),encodeString);
                    break;
                }
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();;
        }
        return retValue;
    }

    /**
     * 设置cookie
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue){
        setCookie(request,response,cookieName,cookieValue,-1);
    }
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxge){
        setCookie(request,response,cookieName,cookieValue,cookieMaxge,false);
    }
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,boolean isEncode){
        setCookie(request,response,cookieName,cookieValue,-1,isEncode);
    }
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cooikeMaxage,boolean isEncode){
        doSetCookie(request,response,cookieName,cookieValue,cooikeMaxage,isEncode);
    }
    public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxage,encodeString);
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookieName
     */
    public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
        doSetCookie(request,response,cookieName,"",-1,false);
    }

    private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
        try{
            if (cookieValue==null){
                cookieValue="";
            }else if(isEncode){
                cookieValue= URLEncoder.encode(cookieValue,"utf-8");
            }
            Cookie cookie=new Cookie(cookieName,cookieValue);
            if(cookieMaxage>0){
                cookie.setMaxAge(cookieMaxage);}
            if (null!=request){
                String domainName= getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final void doSetCookie(HttpServletRequest request,HttpServletResponse response,
                                          String cookieName,String cookieValue,int cookieMaxage,String encodeString){
        try{
            if(cookieValue==null){
                cookieValue="";
            }else{
                cookieValue=URLEncoder.encode(cookieValue,encodeString);
            }
            Cookie cookie =new Cookie(cookieName,cookieValue);
            if(cookieMaxage>0){
                cookie.setMaxAge(cookieMaxage);
            }
            if (null!=request){
                String domainName= getDomainName(request);
                System.out.println(domainName);
                if(!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 得到cookie的域名
     */
    private static String getDomainName(HttpServletRequest request){
        String domainName=null;
        String serverName=request.getRequestURI().toString();//通过request对象获取访问的url地址
        if (serverName==null||serverName.equals("")){
            domainName="";
        }else {
            serverName=serverName.toLowerCase();
            if(serverName.startsWith("http://")){//如果是以http开头
                serverName=serverName.substring(7);
            }
            int end=serverName.length();
            if (serverName.contains("/")){
                end=serverName.indexOf("/");//得到第一个“/”出现的位置
            }
            serverName=serverName.substring(0,end);//截取
            final String[] domains=serverName.split("\\.");
            int len=domains.length;
            if (len>3){
                domainName=domains[len-3]+"."+domains[len-2]+"."+domains[len-1];
            }else if(len<=3&&len>1){
                domainName=domains[len-2]+"."+domains[len-1];
            }else {
                domainName=serverName;
            }
        }
        if(domainName!=null&&domainName.indexOf(":")>0){
            String[] ary=domainName.split("\\:");
            domainName=ary[0];
        }
        return domainName;
    }
}
