/*
 * Created on 11/02/2005
 *
 */
package com.fabricio.sevents.api.util.excecoes;

/**
 * @author luizcgr
 *
 */
public class ExcecaoUtil extends Excecao {
  private static final long serialVersionUID = 3256726177828778035L;

  public ExcecaoUtil() {
    super();
  }

  public ExcecaoUtil(String message) {
    super(message);
  }

  public ExcecaoUtil(Throwable cause) {
    super(cause);
  }

  public ExcecaoUtil(String message, Throwable cause) {
    super(message, cause);
  }
}
