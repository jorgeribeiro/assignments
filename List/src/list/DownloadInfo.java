package list;

public class DownloadInfo implements Comparable<DownloadInfo> {
	String title;
	int numDownloads;
	 
	public DownloadInfo(String t, int downloads) { //constructor
		title = t;
		numDownloads = downloads;
	}
	public DownloadInfo() { //default constructor
		title = "";
		numDownloads = 0;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumDownloads() {
		return numDownloads;
	}
	public void setNumDownloads(int numDownloads) {
		this.numDownloads = numDownloads;
	}
	
	public void incNumDownloads() {
		numDownloads++;
	}
	
	public String toString() {
		String toReturn = title + ": " + numDownloads;
		return toReturn;
	}
	
	@Override
	public int compareTo(DownloadInfo o) {
		if(this.numDownloads > o.numDownloads)
			return 1;
		else if(this.numDownloads < o.numDownloads)
			return -1;
		else return this.title.compareTo(o.title);
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof DownloadInfo) {
			DownloadInfo other = (DownloadInfo) obj;
			return this.title.equals(other.title) && this.numDownloads == other.numDownloads;
		} else {
			return false;
		}
	}
}
