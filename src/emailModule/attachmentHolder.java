package emailModule;

public class attachmentHolder {
    public  String Path;
    public String ContentType; // Ex : image/png , application/pdf etc.
	public attachmentHolder(String givenpath, String givenContentType) {
		// TODO Auto-generated constructor stub
		this.Path = givenpath;
		this.ContentType = givenContentType;
	}
}
