package inwaiders.redn.rpg.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LAS
{

	/*public static List OverRead(String fileName) throws IOException
	{
		BufferedReader reader = null;
		FileWriter writer = null;

		try
		{
			reader = new BufferedReader(new FileReader(fileName + ".txt"));
		}
		catch (FileNotFoundException e)
		{
			writer = new FileWriter(fileName + ".txt", true);
			reader = new BufferedReader(new FileReader(fileName + ".txt"));
		}

		String line;

		List<String> lines = new ArrayList<String>();

		while ((line = reader.readLine()) != null)
		{
			lines.add(line);
		}
		reader.close();
		writer.close();
		return lines;
	}

	public static void setLineOnNumberLine(String fileName, int lineToPars, String add) throws IOException
	{
		List<String> lines = OverRead(fileName);

		if (lines.size() == 0)
		{
			lines.add(add);
			System.out.println("Zero");
		}

		FileWriter writer = new FileWriter(fileName + ".txt", false);

		if (lineToPars < lines.size())
			lines.set(lineToPars, add);
		else
		{

			for (int k = 0; k < lines.size(); k++)
			{
				writer.write(lines.get(k));
				writer.write("\r\n");
			}
			writer.write(add);
			writer.flush();
			writer.close();
			return;
		}

		for (int k = 0; k < lines.size(); k++)
		{
			writer.write(lines.get(k));
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
	}

	public static void deleteFile(String fileName)
	{

		File file = new File(fileName + ".txt");
		file.delete();
	}

	public static void setInteger(String playerName_fileName, String name, int par) throws FileNotFoundException, IOException
	{

		List<String> readetInfo = OverRead(playerName_fileName);

		if (readetInfo.size() == 0)
		{
			setLineOnNumberLine(playerName_fileName, 0, name + " " + par);
			System.out.println("Zero");
			return;
		}
		for (int i = 0; i < readetInfo.size(); i++)
		{

			String[] line_words = readetInfo.get(i).trim().split("\\s+");

			if (line_words.length > 0)
			{

				if (line_words[0].equals(name))
				{
					setLineOnNumberLine(playerName_fileName, i, name + " " + par);
					return;
				}

			}

		}

		setLineOnNumberLine(playerName_fileName, readetInfo.size(), name + " " + par);

	}

	public static int getInteger(String playerName_fileName, String name) throws FileNotFoundException, IOException
	{

		List<String> readetInfo = OverRead(playerName_fileName);

		for (int i = 0; i < readetInfo.size(); i++)
		{

			String[] line_words = readetInfo.get(i).trim().split("\\s+");

			if (line_words.length > 0)
			{

				if (line_words[0].equals(name))
				{
					return Integer.parseInt(line_words[1]);
				}

			}

		}

		return -1;
	}

	public static void setString(String playerName_fileName, String name, String par) throws FileNotFoundException, IOException
	{

		List<String> readetInfo = OverRead(playerName_fileName);

		if (readetInfo.size() == 0)
		{
			setLineOnNumberLine(playerName_fileName, 0, name + " " + par);
			System.out.println("Zero");
			return;
		}
		for (int i = 0; i < readetInfo.size(); i++)
		{

			String[] line_words = readetInfo.get(i).trim().split("\\s+");

			if (line_words.length > 0)
			{

				if (line_words[0].equals(name))
				{
					setLineOnNumberLine(playerName_fileName, i, name + " " + par);
					return;
				}

			}

		}

		setLineOnNumberLine(playerName_fileName, readetInfo.size(), name + " " + par);

	}

	public static String getString(String playerName_fileName, String name) throws FileNotFoundException, IOException
	{

		List<String> readetInfo = OverRead(playerName_fileName);

		for (int i = 0; i < readetInfo.size(); i++)
		{

			String[] line_words = readetInfo.get(i).trim().split("\\s+");

			if (line_words.length > 0)
			{

				if (line_words[0].equals(name))
				{
					return line_words[1];
				}

			}

		}

		return "";
	}*/
}
