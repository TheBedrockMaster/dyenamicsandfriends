import os
from PIL import Image
from PIL.ImageColor import getcolor, getrgb
from PIL.ImageOps import grayscale

colors = {
    "peach": "#BF9873", 
    "aquamarine": "#2C7D7F", 
    "fluorescent": "#EEE9B6", 
    "mint": "#89e8b8", 
    "maroon": "#910000", 
    "bubblegum": "#f771c6", 
    "lavender": "#DD99FF", 
    "persimmon": "#d24119", 
    "cherenkov": "#0198CF",
    "amber": "#D37319",
    "honey": "#ffb446",
    "ultramarine": "#5649ff",
    "spring_green": "#c3ff99",
    "rose": "#ff2865",
    "navy": "#1c2871",
    "icy_blue": "#8FEAFF",
    "wine": "#721E5B",
    "conifer": "#BADA55"
}

def genTextures():
    for path, subdirs, files in os.walk('templates'):
        for name in files:
            if not name.endswith('.png'): continue
            filename = os.path.join(path, name)
            print("F:" + filename)
            for color in colors:
                resFile = filename.replace("$$", color).replace("templates\\", "src\\main\\resources\\")

                image = Image.open(filename)
                if (os.path.isfile(filename.replace(".png", "_overlay_" + color + ".png"))):
                    overlay = Image.open(filename.replace(".png", "_overlay_" + color + ".png"))
                    overlayImage(colorImage(image, colors[color]), overlay).save(resFile)
                elif (os.path.isfile(filename.replace(".png", "_overlay.png"))):
                    overlay = Image.open(filename.replace(".png", "_overlay.png"))
                    overlayImage(colorImage(image, colors[color]), overlay).save(resFile)
                else:
                    colorImage(image, colors[color]).save(resFile)

def colorImage(image, color='#ffffff'):
    image.load()
    split = image.split()

    mult = tuple(map(lambda i: i/255, getrgb(color)))
    for i in range(3):
        split[i].paste(split[i].point(lambda band: band * mult[i]))

    return Image.merge(image.mode, split)

def overlayImage(image, image2):
    image.load()
    image.paste(image2, (0,0), mask = image2)
    return image

genTextures()