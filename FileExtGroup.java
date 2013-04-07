package russosoftware.fileutilities.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FileExtGroup
{
	public static FileExtGroup SOUND_FILE_EXTS = new FileExtGroup(new FileExtension[]{FileExtension.OGG, FileExtension.WAV});
	
	public List<FileExtension> fileExtensions = new ArrayList<FileExtension>();
	
	public FileExtGroup(FileExtension[] fileExtensionArray)
	{
		fileExtensions.addAll(Arrays.asList(fileExtensionArray));
	}
	
	public FileExtGroup(FileExtension fileExtension)
	{
		fileExtensions.add(fileExtension);
	}
	
	public void add(FileExtension extension)
	{
		if(!fileExtensions.contains(extension))
			fileExtensions.add(extension);
	}
	
	public void remove(FileExtension extension)
	{
		if(fileExtensions.contains(extension))
			fileExtensions.remove(extension);
	}
	
	public List<FileExtension> getFileExtensions()
	{
		return this.fileExtensions;
	}
}
