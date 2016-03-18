/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Valentin Tablan 02/10/2001
 *
 *  $Id: OffsetComparator.java 17530 2014-03-04 15:57:43Z markagreenwood $
 *
 */
package com.silicolife.textmining.core.datastructures.nlptools;

import java.util.Comparator;

import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;

/**
 * Compares annotations by start offsets first, then by end 
 * offset if the start offsets are equal.
 * Example: [5,9] < [6,7] < [6,9] < [7,8]
 */
public class OffsetEntityComparator implements Comparator<IEntityAnnotation> {

  @Override
  public int compare(IEntityAnnotation a1, IEntityAnnotation a2){
    int result;

    // compare start offsets
    result = Long.valueOf(a1.getStartOffset()).compareTo(Long.valueOf(a2.getStartOffset()));

    // if start offsets are equal compare end offsets
    if(result == 0) {
      result = Long.valueOf(a1.getEndOffset()).compareTo(Long.valueOf(a2.getEndOffset()));
    } // if

    return result;
  }
}