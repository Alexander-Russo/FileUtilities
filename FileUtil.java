package russosoftware.fileutilities.src;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FileUtil 
{
	/**
	 * Attempts to safely convert a File object to a URL by first converting the file to a URI, then into a URL.
	 * Self-Handles all Exceptions.
	 * 
	 * @param File to retrieve the URL.
	 * @return URL of the Given file.
	 **/
	public static URL toURL(File file)
	{
		try 
		{
			return file.toURI().toURL();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retrieves all files of a given String FilePath by {@code FileType}.
	 * 
	 * @param String for the File Directory Path.
	 * @param FileType to be retrieved.
	 * @throws IOException
	 * @return A LinkedList of the type File containing all files of the given FileType.
	 **/
	public static LinkedList<File> getFilesByTypeAndDir(String dir, FileExtension type) throws IOException
	{
		return getFilesByTypeAndDir(new File(dir), type);
	}
	
	/**
	 * Retrieves all files of a given Directory by {@code FileType}.
	 * 
	 * @param File to be used as the given File Directory.
	 * @param FileType to be retrieved.
	 * @throws IOException
	 * @return A LinkedList of the type File containing all files of the given FileType. 
	 **/
	public static LinkedList<File> getFilesByTypeAndDir(File fileDir, FileExtension type) throws IOException
	{
		if(fileDir.exists())
		{
			LinkedList<File> listOfFiles = new LinkedList<File>();
			if(fileDir.exists())
			{
				listOfFiles.addAll(getFilesByExt(fileDir, type));
				return listOfFiles;
			}
			return null;
		}
		else
			throw new IOException(String.format("Invalid FilePath. File %s does not exist!", fileDir.getName()));
	} 
	
	/**
	 * A Utility Method designed to sort Files into a List based on their Extension
	 * Secondary Overloaded version of this method does not require an array of the FileExtensions to be sorted, and automatically
	 * sorts files for every extension supported in the FileExtension enumeration.
	 * 
	 * @param File object of the Directory to be searched.
	 * @param Array of FileExtensions to be retrieved from the given Directory.
	 * @return A Map, with the Key being the FileExtension being referenced and the Value being the List of all the files of that type.
	 **/
	public static Map<FileExtension, List<File>> sortFilesByExt(File dir, FileExtension[] fileTypes)
	{
		Map<FileExtension, List<File>> fileTypeMap = new HashMap<FileExtension, List<File>>();
		for(FileExtension fileType : fileTypes)
		{
			fileTypeMap.put(fileType, getFilesByExt(dir, fileType));
		}
		return fileTypeMap;
	}
	
	/**
	 * Retrieves files of all FileTypes established in a given {@code FileExtGroup}.
	 * 
	 * @param Directory to be Searched for corresponding File Extensions
	 * @param {@code FileExtGroup} containing all File Types to be contained in the returned List.
	 * @return A List of all Files of the types established in the given FileExtGroup.
	 **/
	public static LinkedList<File> sortFilesByGroup(File dir, FileExtGroup group)
	{
		if(!dir.exists())
			return null;
		
		LinkedList<File> groupFiles = new LinkedList<File>();
		for(FileExtension typ : group.getFileExtensions())
		{
			groupFiles.addAll(getFilesByExt(dir, typ));
		}
		return groupFiles;
	}

	/**
	 * A Utility Method designed to sort Files into a List based on all available Extensions
	 * Secondary Overloaded version of this method requires specified Extensions and sorts out
	 * files only if they contain one of the listed Extensions.
	 * 
	 * @param File object of the Directory to be searched.
	 * @return A Map, with the Key being the FileExtension being referenced and the Value being the List of all the files of that type.
	 **/
	public static Map<FileExtension, List<File>> sortFileByExt(File dir)
	{
		return sortFilesByExt(dir, FileExtension.getRegisteredValues());
	}
	
	/**
	 * Retrieves a List of all files of the specified File Type from the specified Directory. 
	 * 
	 * @param File object of the Directory to be searched.
	 * @param {@code FileExtension} to be used for retrieval.
	 * @return List of Files of File Type.
	 **/
	public static LinkedList<File> getFilesByExt(File dir, FileExtension fileType)
	{
		LinkedList<File> listOfFiles = new LinkedList<File>();
		for(String fileExt : fileType.getExtensions())
		{
			listOfFiles.addAll(Arrays.asList(dir.listFiles(newFilenameFilter(fileExt))));
		}
		return listOfFiles;
	}
	
	/**
	 * Creates a new FilenameFilter to sort out files of based on Extension.
	 * 
	 * @param FileExtension enumeration declaration to be used as the FileType to be sorted.
	 **/
	public static FilenameFilter newFilenameFilter(final String ext)
	{
		return new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name) 
			{
				return name.toLowerCase().endsWith(ext);
			}
		};
	}
}
