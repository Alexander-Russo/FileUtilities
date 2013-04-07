package russosoftware.fileutilities.src;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
	
public final class FileExtension
{	
	private static List<FileExtension> fileExtensions = new LinkedList<FileExtension>();
	
	public static final FileExtension BMP = new FileExtension(Arrays.asList(".bmp"), "Microsoft Windows Bitmap formatted image");
	public static final FileExtension DOC = new FileExtension(Arrays.asList(".doc"), "Microsoft Word Document");
	public static final FileExtension DOCX = new FileExtension(Arrays.asList(".docx"), "XML Format for storing Word-Processing information or spreadsheets");
	public static final FileExtension GIF = new FileExtension(Arrays.asList(".gif"), "Graphics Interchange Format");
	public static final FileExtension HTML = new FileExtension(Arrays.asList(".html"), "HyperText Markup Language");
	public static final FileExtension INFO = new FileExtension(Arrays.asList(".info"), "A Text Information File");
	public static final FileExtension JPEG = new FileExtension(Arrays.asList(".jpg", ".jpeg"), "Joint Photographic Experts Group");
	public static final FileExtension LANG = new FileExtension(Arrays.asList(".lang"), "A Language File");
	public static final FileExtension OGG = new FileExtension(Arrays.asList(".ogg"), "An OGG Sound File");
	public static final FileExtension PDF = new FileExtension(Arrays.asList(".pdf"), "A Portable Document Format");
	public static final FileExtension PDN = new FileExtension(Arrays.asList(".pdn"), "Paint.NET Image File");
	public static final FileExtension RICHTEXT = new FileExtension(Arrays.asList(".rtf"), "A Rich Text File");
	public static final FileExtension PNG = new FileExtension(Arrays.asList(".png"), "Portable Network Graphic");
	public static final FileExtension RAW = new FileExtension(Arrays.asList(".ari", ".arw", ".bay", ".crw", ".cr2", ".cap", ".raw"), "Raw image file, typically from ");
	public static final FileExtension TEXT = new FileExtension(Arrays.asList(".txt"), "A text file");
	public static final FileExtension WAV = new FileExtension(Arrays.asList(".wav"), "A Wave Sound File");
	public static final FileExtension XML = new FileExtension(Arrays.asList(".xml"), "An XML File");
	
	private final List<String> fileExts = new LinkedList<String>();
	private final String fileDesc;
	
	public FileExtension(String fileExt, String fileDesc)
	{
		this(Arrays.asList(fileExt), fileDesc);
	}
	
	public FileExtension(List<String> fileExts, String fileDesc)
	{
		this.fileExts.addAll(fileExts);
		this.fileDesc = fileDesc;
		
		fileExtensions.add(this);
	}
		
	public List<String> getExtensions() 
	{
		return this.fileExts;
	}
		
	public String getDesc()
	{
		return fileDesc;
	}
	
	public void add(String extension) 
	{
		fileExts.add(extension);
	}
	
	/**
	 * Removes the given String Object from the list of File Extensions give for this FileExtension type.
	 * 
	 * @param The String Object to be removed.
	 **/
	public void remove(String extension)
	{
		fileExts.remove(extension);
	}
	
	
	public static FileExtension[] getRegisteredValues() 
	{
		return fileExtensions.toArray(new FileExtension[fileExtensions.size()]);
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(String str : this.getExtensions())
		{
			builder.append(str);
		}
		builder.append(' ');
		builder.append(this.getDesc());
		return builder.toString();
	}

	public FileExtension copy() 
	{
		return new FileExtension(this.getExtensions(), this.getDesc());
	}
	
	public FileExtension combineFileExtension(FileExtension extension)
	{
		LinkedList<String> listOfExtensions = new LinkedList<String>();
		listOfExtensions.addAll(this.getExtensions());
		listOfExtensions.addAll(extension.getExtensions());
		return new FileExtension(listOfExtensions, this.getDesc() + " and " + extension.getDesc());
	}
	
	public FileExtension combineFileExtension(FileExtension extension, String newDesc)
	{
		LinkedList<String> listOfExtensions = new LinkedList<String>();
		listOfExtensions.addAll(this.getExtensions());
		listOfExtensions.addAll(extension.getExtensions());
		return new FileExtension(listOfExtensions, newDesc);
	}
}