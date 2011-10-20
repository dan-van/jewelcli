/*
 * Copyright 2007 Tim Wood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.flamingpenguin.jewel.cli;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.lexicalscope.fluentreflection.ReflectedMethod;

class OptionSpecificationBuilder {
    private final Method m_method;
    private Class<?> m_type;
    private boolean m_multiValued;
    private ReflectedMethod optionalityMethod;
    private final List<String> m_shortNames = new ArrayList<String>();
    private String m_longName;
    private String m_description;
    private String m_pattern;
    private List<String> m_defaultValue;
    private boolean m_helpRequest;

    public OptionSpecificationBuilder(final Method method) {
        m_method = method;
    }

    public void setType(final Class<?> type) {
        m_type = type;
    }

    public void setMultiValued(final boolean multiValued) {
        m_multiValued = multiValued;
    }

    public void setOptionalityMethod(final ReflectedMethod optionalityMethod) {
        this.optionalityMethod = optionalityMethod;
    }

    public void setShortNames(final List<String> shortNames) {
        m_shortNames.addAll(shortNames);
    }

    public void setLongName(final String longName) {
        m_longName = longName;
    }

    public void setDescription(final String description) {
        m_description = description;
    }

    public void setPattern(final String pattern) {
        m_pattern = pattern;
    }

    public void setDefaultValue(final List<String> defaultValue) {
        m_defaultValue = defaultValue;
    }

    public void setHelpRequest(final boolean helpRequest) {
        m_helpRequest = helpRequest;
    }

    public OptionSpecificationImpl createOptionSpecification() {
        final OptionName optionName = new OptionName(m_longName, m_shortNames, m_description);
        final OptionType optionType = new OptionType(m_type, m_pattern, m_multiValued);
        final OptionContext optionContext = new OptionContext(m_defaultValue, m_helpRequest);

        return new OptionSpecificationImpl(optionName,
                                            optionType,
                                            optionContext,
                                            m_method,
                                            optionalityMethod);
    }
}