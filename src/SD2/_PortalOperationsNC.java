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

public interface _PortalOperationsNC
{
    void registerStreamer(StreamInfo s);

    void closeStreamer(StreamInfo s);

    StreamInfo[] getStreamList();
}
