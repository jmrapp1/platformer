package com.jmr.platformer.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Model {

	protected ArrayList<ModelPiece> pieces = new ArrayList<ModelPiece>();
	
	public Model(FileHandle file) {
		loadModel(file);
	}
	
	public Model(Texture texture, String name) {
		pieces.add(new ModelPiece(texture, name, Vector2.Zero));
	}
	
	public ModelPiece getPiece(String name) {
		for (ModelPiece mp : pieces) 
			if (mp.getName().equalsIgnoreCase(name))
				return mp;
		return null;
	}
	
	public void dispose() {
		for (ModelPiece mp : pieces)
			mp.dispose();
	}
	
	public void scale(float amount) {
		for (ModelPiece mp : pieces)
			mp.scale(amount);
	}
	
	public abstract void render(SpriteBatch sb, Vector2 pos);
	
	private void loadModel(FileHandle file) {
		BufferedReader br = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			br = new BufferedReader(file.reader());
			String line;
			while ((line = br.readLine()) != null) 
				lines.add(line);
		} catch (IOException e) {
			System.out.println("Cant load model from: " + file);
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		for (String line : lines) {
			int fQuote = line.indexOf("\"") + 1;
			int sQuote = line.substring(fQuote + 1).indexOf("\"") + 1 + fQuote;
			String name = line.substring(fQuote, sQuote);
			
			String left = line.substring(sQuote + 1);
			fQuote = left.indexOf("\"") + 1;
			sQuote = left.substring(fQuote + 1).indexOf("\"") + 1 + fQuote;
			String x = left.substring(fQuote, sQuote);
			
			left = left.substring(sQuote + 1);
			fQuote = left.indexOf("\"") + 1;
			sQuote = left.substring(fQuote + 1).indexOf("\"") + 1 + fQuote;
			String y = left.substring(fQuote, sQuote);
			
			left = left.substring(sQuote + 1);
			fQuote = left.indexOf("\"") + 1;
			sQuote = left.substring(fQuote + 1).indexOf("\"") + 1 + fQuote;
			String path = left.substring(fQuote, sQuote);
			
			pieces.add(new ModelPiece(new Texture(path), name, new Vector2(Integer.parseInt(x), Integer.parseInt(y))));
		}
	}

	public abstract float getWidth();
	
	public abstract float getHeight();

}
