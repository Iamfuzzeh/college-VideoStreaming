# Video Streaming

This assignment requires the implementation of a streaming facility. All the code must be written in Java, C/C++ or Python, with our recommendation being Java. The system must have exactly
three components, as depicted below:

## Streamer
The streamer is responsible for streaming one video. But multiple streamers can and should be present in the system.

### Mandatory features:

- Register in the portal as a provider of a video stream. The registration information must include: 
  1. stream name
  2. endpoint (transport, ip, port)
  3. video size 
  4. bitrate
  5. associated set of keywords, e.g.,{avideostreamname, {tcp, 127.0.0.1, 10000}, 480x270, 400, {basketball, Cavs, indoor, sports}}
- When closing a stream, the portal should be notified (through a message)
- Use ffmpeg to transcode the video to the desired format, e.g.:
```
ffmpeg −f mpegts −i videofile.mp4 −loglevel warning −analyzeduration 500 k −probesize 500 k −r 30 −s 640x360 −c : v libx264 −preset ultrafast −pix_fmt yuv420p −tune zerolatency −preset ultrafast −b : v 500 k −g 30 −c : a libfdkaac −profile :a aac_he −b : a 32 k −f mpegts tcp://127.0.0.1:10000?listen=1
```

- Video codec (-c:v): libx
- Audio codec (-c:a): libfdkaac
- Bitrate video (-b:v): target bitrate, e.g., 500k (Kbits/s)
- Bitrate audio (-b:a): target bitrate, e.g., 32k (Kbits/s)
- Video size (-s), e.g., 640x
- Output is a TCP server listening at 127.0.0.1, port 10000. It only supports one client at a time.
- Create a streaming server using sockets:
- Connects to ffmpeg output and buffers the current stream.
- Create a server socket that listens to connections, and serves the streaming data (previously got from ffmpeg)

## Portal
### Mandatory features

+ For streamers:
    - Registers streamers to allow a new video (Ice RPC)
    - Handle the closure of a streamer (Ice RPC)
+ For clients:
    - Send stream list (Ice RPC)
+ Portal:
    - Create a ICE publisher to announce new or delete streams (IceStorm)

## Client

### Mandatory features
- Get stream list from portal (Ice RPC)
- Subscribe to portal publisher to receive notifications of new or deleted streams (IceStorm)
- Use ffplay to play streams, e.g., ”ffplay tcp://127.0.0.1:10000”
- In Java, you can use ”ProcessBuilder” for launch a ffplay client.

The client must implement a CLI that supports the following commands:
1.stream list: List all streams
2.stream search "keywords": List all streams that match ”keywords”
3.stream play "name": Play stream ”name” (it should open a ffplay and return to the CLI, so non-blocking)

## How To

Note: The built project is available in the 'build' folder.

1. Run Portal with: `$ java -cp SD-iceRPC.jar Portal`
1. Run one or more Streamers with: `$ java -cp SD-iceRPC.jar Streamer < INPUT`
1. Run one or more Clients with: `$java -cp SD-iceRPC.jar Client PORT`
1. In the Client:
    + `$ stream list`
    + `$ stream search KEYWORD1 KEYWORD2 ...`
    + `$ stream play STREAMNAME`
