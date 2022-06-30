from pytube import YouTube
import pytube.request
import sys


pytube.request.default_range_size = 437184  # 400KB chunk size

video_url = sys.argv[1]
download_choice = sys.argv[2]


def on_complete(stream, filePath):
    print('Complete')


def on_progress(stream, chunk, bytes_remaining):
    filesize = stream.filesize
    percent = str(int(100-(100*bytes_remaining/filesize)))
    print(percent)


video_object = YouTube(
    video_url,
    on_progress_callback=on_progress,
    on_complete_callback=on_complete)


match download_choice:
    case 'a':
        video_object.streams.get_highest_resolution().download()
    case 'b':
        video_object.streams.get_lowest_resolution().download()
    case 'c':
        video_object.streams.get_audio_only().download()
