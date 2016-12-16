package com.silicolife.textmining.core.datastructures.annotation;


/**
 * Simple class to save annotation positions
 * 
 * @author Hugo Costa
 *
 */
public class AnnotationPosition implements Cloneable,Comparable<AnnotationPosition>{
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnnotationPosition other = (AnnotationPosition) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}

	private int start;
	private int end;
	private String originalText;
	private String extactTextAnnotation;

	
	public AnnotationPosition(int start, int end){
		this.start = start;
		this.end = end;
		this.originalText = null;
		this.extactTextAnnotation = null;
	}
	
	public AnnotationPosition(int start, int end,String extactTextAnnotation,String originalText){
		this.start = start;
		this.end = end;
		this.setOriginalText(originalText);
		this.setExtactTextAnnotation(extactTextAnnotation);
	}
	
	public boolean contain(AnnotationPosition a){
		return ( a.getStart()>=getStart() && a.getEnd()<=getEnd() )  // inside condition
			|| ( a.getEnd() <= getEnd() && a.getEnd() > getStart() && a.getStart() < getStart())  // low Bound
			|| ( a.getStart()>=getStart() && a.getStart() < getEnd() && a.getEnd() > getEnd()) // Upper bound
			|| ( a.getStart()<=getStart() && a.getEnd()>=getEnd()); // major condition
	}
	
	public boolean equals(AnnotationPosition pos)
	{
		return ((this.getStart() == pos.getStart()) && (this.getEnd() == pos.getEnd()));		
	}
	
	public int compareTo(AnnotationPosition pos)
	{
		if(this.equals(pos))
		{
			return 0;
		}
		else
		{
			if(this.getStart()>pos.getStart())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
	}
	
	public AnnotationPosition clone()
	{
		return new AnnotationPosition(this.getStart(),this.getEnd());
	}
	
	public String toString()
	{
		return getStart()+" <> "+getEnd();
	}

	public int getStart() {return start;}
	public void setStart(int start) {this.start = start;}
	public int getEnd() {return end;}
	public void setEnd(int end) {this.end = end;}

	public int lenght() {
		return end-start;
	}

	public String getOriginalText() {
		return originalText;
	}
	
	public String getExtactTextAnnotation(){
		return extactTextAnnotation;
	}

	public void setExtactTextAnnotation(String extactTextAnnotation) {
		this.extactTextAnnotation = extactTextAnnotation;
	}
	
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	
	public int diference()
	{
		return getEnd()-getStart();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}
}
