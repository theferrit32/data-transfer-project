package org.datatransferproject.transfer.helium.files;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class HeliumConnection<T> implements Iterable <T> {

    private HeliumClient heliumClient;
    //private String url;
    private HeliumConnectionIterator<T> iterator;
    public HeliumConnection(HeliumClient heliumClient) {
        //this.url = url;
        this.heliumClient = heliumClient;
    }


    public static class HeliumConnectionIterator<T> implements Iterator<T> {
        private HeliumConnection<T> connection;
        public HeliumConnectionIterator (HeliumConnection<T> connection) {
            this.connection = connection;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super T> consumer) {

        }
    }
    @Override
    public Iterator<T> iterator() {
        return new HeliumConnectionIterator<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
