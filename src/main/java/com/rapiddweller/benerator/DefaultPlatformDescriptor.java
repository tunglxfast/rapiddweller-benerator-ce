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

package com.rapiddweller.benerator;

import com.rapiddweller.benerator.engine.parser.xml.XMLStatementParser;
import com.rapiddweller.common.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the {@link PlatformDescriptor} interface.<br/><br/>
 * Created: 07.12.2011 18:58:25
 * @author Volker Bergmann
 * @since 0.7.4
 */
public class DefaultPlatformDescriptor implements PlatformDescriptor {

  private final String name;
  private final List<String> packages;

  public DefaultPlatformDescriptor(String name, String rootPackage) {
    this.name = name;
    this.packages = new ArrayList<>();
    if (rootPackage != null) {
      addPackage(rootPackage);
    }
  }

  @Override
  public String getName() {
    return name;
  }

  protected void addPackage(String rootPackage) {
    this.packages.add(rootPackage);
  }

  @Override
  public String[] getPackagesToImport() {
    return CollectionUtil.toArray(packages, String.class);
  }

  @Override
  public String[] getClassesToImport() {
    return new String[0];
  }

  @Override
  public XMLStatementParser[] getParsers() {
    return new XMLStatementParser[0];
  }

  @Override
  public String toString() {
    return name;
  }

}
