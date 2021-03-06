// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `Portal.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SD2;

public final class PortalPrxHelper extends Ice.ObjectPrxHelperBase implements PortalPrx
{
    private static final String __closeStreamer_name = "closeStreamer";

    public void closeStreamer(StreamInfo s)
    {
        closeStreamer(s, null, false);
    }

    public void closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx)
    {
        closeStreamer(s, __ctx, true);
    }

    private void closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "closeStreamer", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __delBase = __getDelegate(false);
                    _PortalDel __del = (_PortalDel)__delBase;
                    __del.closeStreamer(s, __ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s)
    {
        return begin_closeStreamer(s, null, false, null);
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx)
    {
        return begin_closeStreamer(s, __ctx, true, null);
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s, Ice.Callback __cb)
    {
        return begin_closeStreamer(s, null, false, __cb);
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_closeStreamer(s, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s, Callback_Portal_closeStreamer __cb)
    {
        return begin_closeStreamer(s, null, false, __cb);
    }

    public Ice.AsyncResult begin_closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx, Callback_Portal_closeStreamer __cb)
    {
        return begin_closeStreamer(s, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_closeStreamer(StreamInfo s, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __closeStreamer_name, __cb);
        try
        {
            __result.__prepare(__closeStreamer_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            s.__write(__os);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_closeStreamer(Ice.AsyncResult __result)
    {
        __end(__result, __closeStreamer_name);
    }

    private static final String __getStreamList_name = "getStreamList";

    public StreamInfo[] getStreamList()
    {
        return getStreamList(null, false);
    }

    public StreamInfo[] getStreamList(java.util.Map<String, String> __ctx)
    {
        return getStreamList(__ctx, true);
    }

    private StreamInfo[] getStreamList(java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "getStreamList", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("getStreamList");
                    __delBase = __getDelegate(false);
                    _PortalDel __del = (_PortalDel)__delBase;
                    return __del.getStreamList(__ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_getStreamList()
    {
        return begin_getStreamList(null, false, null);
    }

    public Ice.AsyncResult begin_getStreamList(java.util.Map<String, String> __ctx)
    {
        return begin_getStreamList(__ctx, true, null);
    }

    public Ice.AsyncResult begin_getStreamList(Ice.Callback __cb)
    {
        return begin_getStreamList(null, false, __cb);
    }

    public Ice.AsyncResult begin_getStreamList(java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_getStreamList(__ctx, true, __cb);
    }

    public Ice.AsyncResult begin_getStreamList(Callback_Portal_getStreamList __cb)
    {
        return begin_getStreamList(null, false, __cb);
    }

    public Ice.AsyncResult begin_getStreamList(java.util.Map<String, String> __ctx, Callback_Portal_getStreamList __cb)
    {
        return begin_getStreamList(__ctx, true, __cb);
    }

    private Ice.AsyncResult begin_getStreamList(java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__getStreamList_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __getStreamList_name, __cb);
        try
        {
            __result.__prepare(__getStreamList_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            __result.__writeEmptyParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public StreamInfo[] end_getStreamList(Ice.AsyncResult __result)
    {
        Ice.AsyncResult.__check(__result, this, __getStreamList_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            StreamInfo[] __ret;
            __ret = StreamListHelper.read(__is);
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    private static final String __registerStreamer_name = "registerStreamer";

    public void registerStreamer(StreamInfo s)
    {
        registerStreamer(s, null, false);
    }

    public void registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx)
    {
        registerStreamer(s, __ctx, true);
    }

    private void registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "registerStreamer", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __delBase = __getDelegate(false);
                    _PortalDel __del = (_PortalDel)__delBase;
                    __del.registerStreamer(s, __ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s)
    {
        return begin_registerStreamer(s, null, false, null);
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx)
    {
        return begin_registerStreamer(s, __ctx, true, null);
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s, Ice.Callback __cb)
    {
        return begin_registerStreamer(s, null, false, __cb);
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_registerStreamer(s, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s, Callback_Portal_registerStreamer __cb)
    {
        return begin_registerStreamer(s, null, false, __cb);
    }

    public Ice.AsyncResult begin_registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx, Callback_Portal_registerStreamer __cb)
    {
        return begin_registerStreamer(s, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_registerStreamer(StreamInfo s, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __registerStreamer_name, __cb);
        try
        {
            __result.__prepare(__registerStreamer_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            s.__write(__os);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_registerStreamer(Ice.AsyncResult __result)
    {
        __end(__result, __registerStreamer_name);
    }

    public static PortalPrx checkedCast(Ice.ObjectPrx __obj)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof PortalPrx)
            {
                __d = (PortalPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    PortalPrxHelper __h = new PortalPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static PortalPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof PortalPrx)
            {
                __d = (PortalPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    PortalPrxHelper __h = new PortalPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static PortalPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    PortalPrxHelper __h = new PortalPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static PortalPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    PortalPrxHelper __h = new PortalPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static PortalPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof PortalPrx)
            {
                __d = (PortalPrx)__obj;
            }
            else
            {
                PortalPrxHelper __h = new PortalPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static PortalPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        PortalPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            PortalPrxHelper __h = new PortalPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::SD2::Portal"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _PortalDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _PortalDelD();
    }

    public static void __write(IceInternal.BasicStream __os, PortalPrx v)
    {
        __os.writeProxy(v);
    }

    public static PortalPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            PortalPrxHelper result = new PortalPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
