package exercise01.bean;

import java.util.Map;

public class Data {
	private static final String[] scan_range= {"**Starting scan**","Scan done."};
	private static final String[] image_range= {"Image TIF saved in","Loading image…"};
	private static final String[] show_range= {"Loading image…","Image showed"};
	private int hour;
	private int scan_average;
	private int image_average;
	private int show_average;
	private int docNum;
	
	public Data() {
		super();
	}

	public Data(int scan_average, int image_average, int show_average, int doc_num) {
		super();
		this.scan_average = scan_average;
		this.image_average = image_average;
		this.show_average = show_average;
		this.docNum = doc_num;
	}
	
	
	
	public static String[] getImageRange() {
		return image_range;
	}
	public static String[] getShowRange() {
		return show_range;
	}
	public int getScan_average() {
		return scan_average;
	}	
	public void setScan_average(int scan_average) {
		this.scan_average = scan_average;
	}
	public int getImage_average() {
		return image_average;
	}
	public void setImage_average(int image_average) {
		this.image_average = image_average;
	}
	public int getShow_average() {
		return show_average;
	}
	public void setShow_average(int show_average) {
		this.show_average = show_average;
	}
	public int getDocNum() {
		return docNum;
	}
	public void setDocNum(int doc_num) {
		this.docNum = doc_num;
	}
	public static String[] getScanRange() {
		return scan_range;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + docNum;
		result = prime * result + image_average;
		result = prime * result + scan_average;
		result = prime * result + show_average;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (docNum != other.docNum)
			return false;
		if (image_average != other.image_average)
			return false;
		if (scan_average != other.scan_average)
			return false;
		if (show_average != other.show_average)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Data [scan_average=" + scan_average + ", image_average=" + image_average + ", show_average="
				+ show_average + ", doc_num=" + docNum + "]";
	}
	
	public void printConsole() {
		System.out.println(String.format("Avrg time to scan: %dms", this.scan_average));
		System.out.println(String.format("Avrg time to save img: %dms", this.image_average));
		System.out.println(String.format("Avrg time to show image: %dms", this.show_average));
	}

}
