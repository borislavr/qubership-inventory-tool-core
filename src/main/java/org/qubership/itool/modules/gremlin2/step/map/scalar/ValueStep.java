/*
 * Copyright 2024-2025 NetCracker Technology Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.qubership.itool.modules.gremlin2.step.map.scalar;

import org.qubership.itool.modules.gremlin2.Traversal;
import org.qubership.itool.modules.gremlin2.Traverser;
import org.qubership.itool.modules.gremlin2.step.AbstractStep;
import org.qubership.itool.modules.gremlin2.util.ValueHelper;


public class ValueStep<S, E> extends ScalarMapStep<S, E> {

    private String valueKey;

    public ValueStep(final Traversal.Admin traversal, String key) {
        super(traversal);
        this.valueKey = key;
        setIgnoreEmptyTraverser(true);
    }

    @Override
    protected E map(Traverser.Admin<S> traverser) {
        Object obj = traverser.get();
        return (E)ValueHelper.getObjectValue(this.valueKey, obj);
    }

    @Override
    protected Traverser<E> generateTraverser(Traverser.Admin<S> previousTraverser, E value) {
        Traverser<E> traverser = previousTraverser.split(
            (previousTraverser.getSource() != null) ? previousTraverser.getSource() : null,
            value, this);
        return traverser;
    }

    @Override
    public AbstractStep<S, E> clone() {
        ValueStep clone = (ValueStep) super.clone();
        clone.valueKey = this.valueKey;
        return clone;
    }

}
