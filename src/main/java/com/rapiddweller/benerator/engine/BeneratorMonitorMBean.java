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

package com.rapiddweller.benerator.engine;

/**
 * MBean interface for monitoring Benerator.<br/><br/>
 * Created: 27.07.2010 21:48:46
 *
 * @author Volker Bergmann
 * @since 0.6.3
 */
public interface BeneratorMonitorMBean {
  /**
   * Gets total generation count.
   *
   * @return the total generation count
   */
  long getTotalGenerationCount();

  /**
   * Gets current throughput.
   *
   * @return the current throughput
   */
  long getCurrentThroughput();

  /**
   * Gets open connection count.
   *
   * @return the open connection count
   */
  int getOpenConnectionCount();

  /**
   * Gets open result set count.
   *
   * @return the open result set count
   */
  int getOpenResultSetCount();

  /**
   * Gets open statement count.
   *
   * @return the open statement count
   */
  int getOpenStatementCount();

  /**
   * Gets open prepared statement count.
   *
   * @return the open prepared statement count
   */
  int getOpenPreparedStatementCount();

  /**
   * Reset.
   */
  void reset();
}
