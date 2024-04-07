package com.example.my_framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class GraphicsFW {
  private AssetManager assetManagerGame;
  //Битмап отвечает за общее хранение всех спрайтов игры, первый битмап frameBuffer , является хранилищем которое
    //растягивается под все размеры телефонов, т.е игра должна выгялдеть на всех телефонах одинаково для этого и нужен frameBuffer
  private Bitmap frameBufferGame;
  private Canvas canvasGame;
  private Paint paintGame;
    // Этот Битмап отвечает за отрисовку текстур в frameBuffer, т.е frameBuffer одна цельная картина которая обновляется и построенная из
    // textureGame-ов
  private Bitmap textureGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame = assetManagerGame;
        this.frameBufferGame = frameBufferGame;
        //здесь идет не просто отрисовка канваса, мы в канвас передаем наш цельный frameBuffer , в который добавлены все текстуры под отдельности,
        // мы не отрисовываем каждую текстуру т.е new Canvas(текстура) , а мы отрисовываем цельный фрейм Canvas(frameBuffer(в котором текстура1,текстура2 и т.д))
        this.canvasGame = new Canvas(frameBufferGame);
        this.paintGame = new Paint();
    }

    public void clearScene(int colorRGB) {
        canvasGame.drawRGB(colorRGB,colorRGB,colorRGB);
    }
    public void drawPixel(int x,int y, int color) {
        paintGame.setColor(color);
        canvasGame.drawPoint(x,y,paintGame);
    }
    public void drawLine(int startX,int startY, int stopX, int stopY, int color) {
        paintGame.setColor(color);
        canvasGame.drawLine(startX,startY,stopX,stopY,paintGame);
    }

    public void drawText(String text, int x , int y, int color, int sizeText, Typeface font) {
        paintGame.setColor(color);
        paintGame.setTextSize(sizeText);
        paintGame.setTypeface(font);
        canvasGame.drawText(text,x,y,paintGame);
    }

    public void drawTexture (Bitmap textureGame,int x, int y) {
        canvasGame.drawBitmap(textureGame,x,y,null);
    }

    public int getWidthFrameBuffer () {
        return frameBufferGame.getWidth();
    }
    public int getHeightFrameBuffer () {
        return frameBufferGame.getHeight();
    }
    public Bitmap newTexture(String fileName) {
        InputStream inputStream = null;

        try {
            //Открываем файл нашей текстуры с помощью InputStream
            inputStream = assetManagerGame.open(fileName);
            // Передаем поток с файлом текстуры и присваеваем его в textureGame
            // try catch ловит ошибки с файлами , а оператор if inputStream!-null при обнаружении что файл не пустой
            // закрывает поток и далее идет передача textureGame уже с присвоенной ему текстурой
            textureGame = BitmapFactory.decodeStream(inputStream);
            if (textureGame==null) {
                throw new RuntimeException("Не возможно загрузить файл: "+fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не возможно загрузить файл: "+fileName);
        }
        if (inputStream!=null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return textureGame;
    }

    public Bitmap newSprite(Bitmap textureAtlas,int x, int y, int widthSprite, int heightSprite) {
        return Bitmap.createBitmap(textureAtlas,x,y,widthSprite,heightSprite);
    }
}
