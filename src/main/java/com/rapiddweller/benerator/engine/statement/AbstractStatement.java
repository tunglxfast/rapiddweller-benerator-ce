/*
 * (c) Copyright 2006-2020 by rapiddweller GmbH & Volker Bergmann. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, is permitted under the terms of the
 * GNU General Public License.
 *
 * For redistributing this software or a derivative work under a license other
 * than the GPL-compatible Free Software License as defined by the Free
 * Software Foundation or approved by OSI, you must first obtain a commercial
 * license to this software product from rapiddweller GmbH & Volker Bergmann.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * WITHOUT A WARRANTY OF ANY KIND. ALL EXPRESS OR IMPLIED CONDITIONS,
 * REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE
 * HEREBY EXCLUDED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.rapiddweller.benerator.engine.statement;

import com.rapiddweller.benerator.engine.Statement;
import com.rapiddweller.benerator.engine.expression.CachedExpression;
import com.rapiddweller.common.Context;
import com.rapiddweller.common.ErrorHandler;
import com.rapiddweller.common.Level;
import com.rapiddweller.script.Expression;

/**
 * Abstract implementation of the Statement interface.<br/><br/>
 * Created: 27.10.2009 20:16:20
 *
 * @author Volker Bergmann
 * @since 0.5.0
 */
public abstract class AbstractStatement implements Statement {

  private final Expression<ErrorHandler> errorHandler;

  // constructors ----------------------------------------------------------------------------------------------------

  /**
   * Instantiates a new Abstract statement.
   */
  protected AbstractStatement() {
    this(null);
  }

  /**
   * Instantiates a new Abstract statement.
   *
   * @param errorHandler the error handler
   */
  protected AbstractStatement(Expression<ErrorHandler> errorHandler) {
    this.errorHandler = errorHandler;
  }

  // Task interface --------------------------------------------------------------------------------------------------

  /**
   * Gets error handler.
   *
   * @param context the context
   * @return the error handler
   */
  public ErrorHandler getErrorHandler(Context context) {
    if (errorHandler == null) {
      return new ErrorHandler(getClass().getName(), Level.fatal);
    }
    return errorHandler.evaluate(context);
  }

  // helpers ---------------------------------------------------------------------------------------------------------

  /**
   * Handle error.
   *
   * @param message the message
   * @param context the context
   */
  protected void handleError(String message, Context context) {
    getErrorHandler(context).handleError(message);
  }

  /**
   * Handle error.
   *
   * @param message the message
   * @param context the context
   * @param t       the t
   */
  protected void handleError(String message, Context context, Throwable t) {
    getErrorHandler(context).handleError(message, t);
  }

  /**
   * Cache expression.
   *
   * @param <T>        the type parameter
   * @param expression the expression
   * @return the expression
   */
  protected static <T> Expression<T> cache(Expression<T> expression) {
    return (expression != null ? new CachedExpression<>(expression) : null);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}
