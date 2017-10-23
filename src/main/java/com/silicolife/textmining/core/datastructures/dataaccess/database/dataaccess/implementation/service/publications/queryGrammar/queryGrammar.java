/* Generated By:JavaCC: Do not edit this line. queryGrammar.java */
package com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.queryGrammar;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.io.UnsupportedEncodingException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;

public class queryGrammar implements queryGrammarConstants {

  private IAnnotationService annotationService = null;

  public HashSet<Long > parseGrammar(String query, IAnnotationService annotationService)  throws ParseException , AnnotationException
  {

    this.annotationService = annotationService;
    HashSet<Long > result = null;

      try
      {
        result =  this.query_p();
        //System.out.println("OK");
      }
      catch (Exception e)
      {
        System.out.println("NOK.");
        System.out.println(e.getMessage());
        this.ReInit(System.in);
      }
      catch (Error e)
      {
        System.out.println("Oops.");
        System.out.println(e.getMessage());

      }
      return result;
    }

//("AND" | "OR") "{" (t(a))+ "}"
  final public HashSet<Long> query_p() throws ParseException, AnnotationException {
 ArrayList<HashSet<Long >> operations = new ArrayList<HashSet<Long >>();
    jj_consume_token(11);
    operation(operations);
    jj_consume_token(12);
    {if (true) return operations.get(0);}
    throw new Error("Missing return statement in function");
  }

  final public void operation(ArrayList<HashSet<Long >> operations) throws ParseException, AnnotationException {
 HashSet<Long > a = new HashSet<Long >();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 13:
      andOp(a, operations);
      break;
    case 16:
      orOp(a, operations);
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
   System.out.println(operations);
  }

  final public void andOp(HashSet<Long> a, ArrayList<HashSet<Long >> operations) throws ParseException, AnnotationException {
  ArrayList<HashSet<Long >> sonOp = new ArrayList<HashSet<Long >>();
   HashSet<Long > res = new HashSet<Long >();
    jj_consume_token(13);
    jj_consume_token(14);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
      case 16:
      case 17:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      t(a, sonOp);
    }
    jj_consume_token(15);
          System.out.println("AND");
          System.out.println(a);

            for(Long rID : a) {
            HashSet<Long > temp =   new HashSet<Long >();
            temp.add(rID);
               if(res.size() ==0) {
          res.addAll(this.annotationService.getPublicationsIdsByResourceElements(temp));
                }
                else {
                  res.retainAll(this.annotationService.getPublicationsIdsByResourceElements(temp));
                }
        }
          for(HashSet<Long > i : sonOp) {
             if(res.size() ==0) {
                        res.addAll(i);
             }else {
                res.retainAll(i);
                }
          }
          operations.add(res);
          System.out.println("SON");
          System.out.println(sonOp);
          //System.out.println(this.annotationService.getPublicationsIdsByResourceElements(a));

  }

  final public void orOp(HashSet<Long > a, ArrayList<HashSet<Long >> operations) throws ParseException, AnnotationException {
  ArrayList<HashSet<Long >> sonOp = new ArrayList<HashSet<Long >>();
  HashSet<Long > res = new HashSet<Long >();
    jj_consume_token(16);
    jj_consume_token(14);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 13:
      case 16:
      case 17:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      t(a, sonOp);
    }
    jj_consume_token(15);
          System.out.println("OR");
          System.out.println(a);
          if(a.size() >0)
          res.addAll(this.annotationService.getPublicationsIdsByResourceElements(a));
          for(HashSet<Long > i : sonOp) {
            res.addAll(i);
          }
          operations.add(res);
          System.out.println("SON");
          System.out.println(sonOp);
          //System.out.println(this.annotationService.getPublicationsIdsByResourceElements(a));

  }

  final public void t(HashSet<Long > a, ArrayList<HashSet<Long >> operations) throws ParseException, AnnotationException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 13:
    case 16:
      operation(operations);
      break;
    case 17:
      el(a);
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void el(HashSet<Long > a) throws ParseException {
 Token id; HashSet<Long> hashWid = new HashSet<Long>();
    jj_consume_token(17);
    id = jj_consume_token(CONSTANT);
           hashWid.add((Long) id.getValue());
           a.add((Long) Long.valueOf(id.toString()));
  }

  /** Generated Token Manager. */
  public queryGrammarTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x12000,0x32000,0x32000,0x32000,};
   }

  /** Constructor with InputStream. */
  public queryGrammar(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public queryGrammar(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new queryGrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public queryGrammar(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new queryGrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public queryGrammar(queryGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(queryGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[18];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 18; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
