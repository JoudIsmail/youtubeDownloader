from pytube import YouTube
import sys

video_url = sys.argv[1]
download_choice = sys.argv[2]


def on_complete(stream, filePath):
    print('Complete')


def on_progress(stream, chunk, bytes_remaining):
    print('Progress')


video_object = YouTube(
    video_url,
    on_complete_callback=on_complete,
    on_progress_callback=on_progress)


match download_choice:
    case 'a':
        video_object.streams.get_highest_resolution().download()
    case 'b':
        video_object.streams.get_lowest_resolution().download()
    case 'c':
        video_object.streams.get_audio_only().download()
