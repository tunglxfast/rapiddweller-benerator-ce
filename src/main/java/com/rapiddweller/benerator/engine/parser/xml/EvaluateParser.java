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

package com.rapiddweller.benerator.engine.parser.xml;

import static com.rapiddweller.benerator.engine.DescriptorConstants.*;
import static com.rapiddweller.benerator.engine.parser.xml.DescriptorParserUtil.*;

import java.util.Set;

import com.rapiddweller.benerator.engine.DescriptorConstants;
import com.rapiddweller.benerator.engine.Statement;
import com.rapiddweller.benerator.engine.expression.ScriptExpression;
import com.rapiddweller.benerator.engine.statement.EvaluateStatement;
import com.rapiddweller.common.CollectionUtil;
import com.rapiddweller.common.converter.String2CharConverter;
import com.rapiddweller.script.Expression;
import com.rapiddweller.script.expression.ConvertingExpression;
import com.rapiddweller.script.expression.FeatureAccessExpression;
import com.rapiddweller.script.expression.StringExpression;
import org.w3c.dom.Element;

/**
 * Parses an &lt;evaluate&gt; element in a Benerator descriptor file.<br/><br/>
 * Created: 25.10.2009 01:01:02
 * @since 0.6.0
 * @author Volker Bergmann
 */
public class EvaluateParser extends AbstractBeneratorDescriptorParser {
	
	private static final Set<String> OPTIONAL_ATTRIBUTES = CollectionUtil.toSet(
			ATT_ID, ATT_URI, ATT_TYPE, ATT_TARGET, ATT_SEPARATOR, ATT_ON_ERROR, ATT_ENCODING, 
			ATT_OPTIMIZE, ATT_INVALIDATE, ATT_ASSERT);

	public EvaluateParser() {
		super(null, null, OPTIONAL_ATTRIBUTES);
	}

	@Override
	public boolean supports(Element element, Statement[] parentPath) {
		String name = element.getNodeName();
	    return DescriptorConstants.EL_EVALUATE.equals(name) 
	    	|| DescriptorConstants.EL_EXECUTE.equals(name);
    }

	@Override
	public EvaluateStatement doParse(Element element, Statement[] parentPath, BeneratorParseContext context) {
		boolean evaluate = DescriptorConstants.EL_EVALUATE.equals(element.getNodeName());
		if (evaluate) {
			assertAtLeastOneAttributeIsSet(element, ATT_ID, ATT_ASSERT);
		} else {
			assertAttributeIsNotSet(element, ATT_ID);
			assertAttributeIsNotSet(element, ATT_ASSERT);
		}
		Expression<String> id           = parseAttribute(ATT_ID, element);
		Expression<String> text         = new StringExpression(parseScriptableElementText(element, false));
		Expression<String> uri          = parseScriptableStringAttribute(ATT_URI, element);
		Expression<String> type         = parseAttribute(ATT_TYPE, element);
		Expression<?> targetObject      = new FeatureAccessExpression<>(element.getAttribute(ATT_TARGET));
		Expression<Character> separator = new ConvertingExpression<>(parseScriptableStringAttribute(ATT_SEPARATOR, element), new String2CharConverter());
		Expression<String> onError      = parseScriptableStringAttribute(ATT_ON_ERROR, element);
		Expression<String> encoding     = parseScriptableStringAttribute(ATT_ENCODING, element);
		Expression<Boolean> optimize    = parseBooleanExpressionAttribute(ATT_OPTIMIZE, element, false);
		Expression<Boolean> invalidate  = parseBooleanExpressionAttribute(ATT_INVALIDATE, element, null);
		Expression<?> assertion         = new ScriptExpression<>(element.getAttribute(ATT_ASSERT));
		return new EvaluateStatement(evaluate, id, text, uri, type, targetObject, separator, onError, encoding, optimize, invalidate, assertion);
	}

}
