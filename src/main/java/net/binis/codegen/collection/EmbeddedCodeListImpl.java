package net.binis.codegen.collection;

/*-
 * #%L
 * code-generator-core
 * %%
 * Copyright (C) 2021 Binis Belev
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import net.binis.codegen.factory.CodeFactory;

import java.util.Comparator;
import java.util.List;

public class EmbeddedCodeListImpl<M, T, R> extends EmbeddedCodeCollectionImpl<M, T, R> {

    private final List<T> list;

    public EmbeddedCodeListImpl(R parent, List<T> list, Class<T> cls) {
        super(parent, list, cls);
        this.list = list;
    }

    @Override
    public EmbeddedCodeCollection<M, T, R> remove(int index) {
        list.remove(index);
        return this;
    }

    @Override
    public EmbeddedCodeCollection<M, T, R> sort(Comparator<? super T> comparator) {
        list.sort(comparator);
        return this;
    }

    @Override
    public M get(int index) {
        return CodeFactory.modify(this, list.get(index));
    }

    @Override
    public M insert(int index) {
        T value = CodeFactory.create(cls);
        list.add(index, value);
        return CodeFactory.modify(this, value);
    }

    @Override
    public M first() {
        return CodeFactory.modify(this, list.get(0));
    }

    @Override
    public M last() {
        return CodeFactory.modify(this, list.get(list.size() - 1));
    }
}
