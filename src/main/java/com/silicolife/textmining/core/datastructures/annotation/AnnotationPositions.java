package com.silicolife.textmining.core.datastructures.annotation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;

/**
 * Class for management Annotation in text 
 * 
 * @author Hugo Costa
 *
 */
public class AnnotationPositions implements Cloneable{

	private SortedMap<AnnotationPosition,IAnnotation> annotations;
	
	public AnnotationPositions(){
		annotations = new TreeMap<AnnotationPosition,IAnnotation>();
	}

	/**
	 * Return a subset of annotation position given offset limits
	 * 
	 * @param startoffset
	 * @param endoffset
	 * @return
	 */
	public AnnotationPositions getAnnotationPositionsSubSet(long startoffset,long endoffset)
	{
		AnnotationPositions annots = new AnnotationPositions();
		for(AnnotationPosition pos:annotations.keySet())
		{
			if(pos.getStart() >= startoffset && pos.getEnd() <= endoffset)
			{
				annots.addAnnotationWhitConflicts(pos, annotations.get(pos));
			}
		}
		return annots;
	}
	
	
	/**
	 * Return a subset of annotation position given offset limits
	 * 
	 * @param startoffset
	 * @param endoffset
	 * @return
	 */
	public Collection<IAnnotation> getAnnotationsSubSet(long startoffset,long endoffset)
	{
		AnnotationPositions annotations = getAnnotationPositionsSubSet(startoffset,endoffset);
		return annotations.getAnnotations().values();
	}

	/**
	 * Method for add annotation testing delimiter conflits. Two entities position can not be in same portion of text
	 * 
	 * @param position
	 * @param entityAnnotation
	 * @return
	 */
	public boolean addAnnotationWhitConflicts(AnnotationPosition position, IAnnotation entityAnnotation){
		for(AnnotationPosition p : annotations.keySet())
		{
			if(p.equals(position))
			{
				// Same position
				if(annotations.get(p) instanceof IEntityAnnotation)
				{
					if(entityAnnotation instanceof IEventAnnotation)
					{
						annotations.put(position, entityAnnotation);
						return true;
					}
					else
					{
						// two same entities whit diferent casesensitives example Hpq and hpq
						if(position.getOriginalText()!=null && position.getOriginalText().length()>0 && position.getExtactTextAnnotation()!=null && position.getExtactTextAnnotation().length()>0)
						{
							String original = position.getOriginalText();
//							IEntityAnnotation ent = (IEntityAnnotation) annotations.get(p);
							// compare if entity that exist is case sensitive equals
							if(original.equals(p.getExtactTextAnnotation()))
							{
								return false;
							}
							else
							{
								// test candidate entity
								IEntityAnnotation ent = (IEntityAnnotation) entityAnnotation;
								if(original.equals(position.getExtactTextAnnotation()))
								{
									annotations.remove(p);
									annotations.put(position, ent);
									return true;
								}
								else
								{
									return false;
								}
							}
						}
						else
						{
							return false;
						}
					}
				}
				return false;
			}
			else if(p.contain(position))
			{
				return false;
			}
		}
		annotations.put(position, entityAnnotation);
		return true;
	}
	
	/**
	 * Method for add annotation testing delimiter conflits and replace if range is more. Two entities position can not be in same portion of texts
	 * 
	 * @param position
	 * @param entityAnnotation
	 * @return
	 */
	public boolean addAnnotationWhitConflitsAndReplaceIfRangeIsMore(AnnotationPosition position, IAnnotation entityAnnotation)
	{
		if(!addAnnotationWhitConflicts(position,entityAnnotation))
		{
			if(entityAnnotation instanceof IEventAnnotation)
			{
				return false;
			}
			if(!annotationHaveConflitLimits(position))
			{
				AnnotationPositions annots = getAnnotationPositionsSubSet(position.getStart(), position.getEnd());
				if(annots.getAnnotations().size()>0)
				{
					for(AnnotationPosition  aux : annots.getAnnotations().keySet())
					{
						annotations.remove(aux);
					}
					annotations.put(position,entityAnnotation);
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	

	
	private boolean annotationHaveConflitLimits(AnnotationPosition position) {
		for(AnnotationPosition pos:annotations.keySet())
		{
			if(pos.equals(position))
			{
				return true;
			}
			if(pos.getStart()<position.getStart() && position.getStart() < pos.getEnd() && pos.getEnd()< position.getEnd() )
			{
				return true;
			}
			else if(pos.getStart()<position.getEnd() && position.getEnd() < pos.getEnd() && pos.getStart()> position.getStart())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Return a {@link AnnotationPosition} for {@link IAnnotation} that contains insideIndex offset
	 * 
	 * @param insideIndex
	 * @return
	 */
	public AnnotationPosition getAnnotationPositionByInsideIndex(int insideIndex)
	{
		for(AnnotationPosition pos:annotations.keySet())
		{
			if(pos.getStart()<=insideIndex && pos.getEnd()>=insideIndex)
			{
				return pos;
			}
			else if(pos.getStart()>=insideIndex)
			{
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Remove annotation
	 * 
	 * @param annotation
	 * @return
	 */
	public boolean removeAnnotation(AnnotationPosition annotation)
	{
		if(annotations.containsKey(annotation))
		{
			annotations.remove(annotation);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public AnnotationPositions clone()
	{
		AnnotationPositions position = new AnnotationPositions();
		for(AnnotationPosition p : annotations.keySet())
		{
			position.addAnnotationWhitConflicts(p.clone(),annotations.get(p).clone());
		}
		return position;
	}
	
	public boolean containsKey(Object key)
	{
		if(key instanceof AnnotationPosition)
		{
			AnnotationPosition posCom = (AnnotationPosition) key;
			for(AnnotationPosition pos:annotations.keySet())
			{
				if(pos.equals(posCom))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public IAnnotation get(Object key)
	{
		if(key instanceof AnnotationPosition)
		{
			AnnotationPosition posCom = (AnnotationPosition) key;
			for(AnnotationPosition pos:annotations.keySet())
			{
				if(pos.equals(posCom))
				{
					IAnnotation elem = annotations.get(pos);
					return elem;
				}
			}
		}
		return null;
	}
	
	/**
	 * Return all Annotations
	 * 
	 * @return
	 */
	public SortedMap<AnnotationPosition, IAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Method for add annotation testing delimiter conflits and replace if range is more. Two entities position can not be in same portion of texts
	 * 
	 * @param position
	 * @param entityAnnotation
	 * @return
	 */
	public boolean addAnnotationWhitConflitsAndReplaceIfRangeIsMoreRule(AnnotationPosition position, IAnnotation entityAnnotation) {
		if(!addAnnotationWhitConflictsRule(position,entityAnnotation))
		{
			return false;
		}
		return true;
		
	}

	private boolean addAnnotationWhitConflictsRule(AnnotationPosition position,IAnnotation entityAnnotaion) {
		for(AnnotationPosition p : annotations.keySet())
		{
			if(p.equals(position))
			{
				return false;
			}
			else if(p.contain(position))
			{
				return false;
			}
		}
		annotations.put(position, entityAnnotaion);
		return true;
	}

	public boolean containsConflits(AnnotationPosition position) {
		for(AnnotationPosition p : annotations.keySet())
		{
			if(p.equals(position))
			{
				return true;
			}
			else if(p.contain(position) || position.contain(p))
			{
				return true;
			}
		}
		return false;
	}
	
	public List<IEntityAnnotation> getEntitiesFromAnnoattionPositions() {
		List<IEntityAnnotation> annotation = new ArrayList<>();
		SortedMap<AnnotationPosition, IAnnotation> annotations = getAnnotations();
		for(IAnnotation annot : annotations.values())
		{
			if(annot instanceof IEntityAnnotation)
				annotation.add((IEntityAnnotation) annot);
		}
		return annotation;
	}

}
