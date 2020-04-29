/* Generated By:JavaCC: Do not edit this line. FirstOrderLogicParser.java */
package Parsers.FirstOrderLogic;

import java.io.StringReader;

public class FirstOrderLogicParser implements FirstOrderLogicParserConstants {
  public static FirstOrderLogicParser parser;
  public static String parse(StringReader stringReader) throws ParseException
  {
    if(parser==null)
    {
      parser=new FirstOrderLogicParser(stringReader);
    }
    else
    {
      ReInit(stringReader);
    }
      try
      {
        if (FirstOrderLogicParser.one_line()==0)
        {
          return "OK";
        }
      }
      catch (Exception e)
      {
        return "NOK."+"\u005cn"+e.getMessage();
      }
      catch (Error e)
      {
        return "Ooops."+"\u005cn"+e.getMessage();
      }
                return "Nope";
    }

    public static String parseFunction(StringReader stringReader) throws ParseException
  {
    if(parser==null)
    {
      parser=new FirstOrderLogicParser(stringReader);
    }
    else
    {
      ReInit(stringReader);
    }
      try
      {
        if (FirstOrderLogicParser.func()==0)
        {
          return "OK";
        }
      }
      catch (Exception e)
      {
        return "NOK."+"\u005cn"+e.getMessage();
      }
      catch (Error e)
      {
        return "Ooops."+"\u005cn"+e.getMessage();
      }
                return "Nope";
    }

  static final public int one_line() throws ParseException {
    binary();
    {if (true) return 0;}
    throw new Error("Missing return statement in function");
  }

  static final public int func() throws ParseException {
    function();
    {if (true) return 0;}
    throw new Error("Missing return statement in function");
  }

  static final public void binary() throws ParseException {
    unary();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONJUNCTION:
      case DISJUNCTION:
      case IMPLICATION:
      case REVERSE_IMPLICATION:
      case DOUBLE_IMPLICATION:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONJUNCTION:
        jj_consume_token(CONJUNCTION);
        break;
      case DISJUNCTION:
        jj_consume_token(DISJUNCTION);
        break;
      case IMPLICATION:
        jj_consume_token(IMPLICATION);
        break;
      case REVERSE_IMPLICATION:
        jj_consume_token(REVERSE_IMPLICATION);
        break;
      case DOUBLE_IMPLICATION:
        jj_consume_token(DOUBLE_IMPLICATION);
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      unary();
    }
  }

  static final public void unary() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NEGATION:
    case EXISTENTIAL_CUANTIFIER:
    case UNIVERSAL_CUANTIFIER:
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEGATION:
          jj_consume_token(NEGATION);
          break;
        case UNIVERSAL_CUANTIFIER:
          jj_consume_token(UNIVERSAL_CUANTIFIER);
          break;
        case EXISTENTIAL_CUANTIFIER:
          jj_consume_token(EXISTENTIAL_CUANTIFIER);
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEGATION:
        case EXISTENTIAL_CUANTIFIER:
        case UNIVERSAL_CUANTIFIER:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_2;
        }
      }
      formula();
      break;
    case FUNCTIONNAME:
    case 15:
      formula();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void formula() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FUNCTIONNAME:
      function();
      break;
    case 15:
      jj_consume_token(15);
      binary();
      jj_consume_token(16);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void function() throws ParseException {
    jj_consume_token(FUNCTIONNAME);
    jj_consume_token(15);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAME:
    case FUNCTIONNAME:
      parameters();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(16);
  }

  static final public void parameters() throws ParseException {
    param();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 17:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      jj_consume_token(17);
      param();
    }
  }

  static final public void param() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NAME:
      jj_consume_token(NAME);
      break;
    case FUNCTIONNAME:
      function();
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public FirstOrderLogicParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x7c0,0x7c0,0x1820,0x1820,0xd820,0xc000,0x6000,0x20000,0x6000,};
   }

  /** Constructor with InputStream. */
  public FirstOrderLogicParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public FirstOrderLogicParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new FirstOrderLogicParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public FirstOrderLogicParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new FirstOrderLogicParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public FirstOrderLogicParser(FirstOrderLogicParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(FirstOrderLogicParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
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
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[18];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
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
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  }