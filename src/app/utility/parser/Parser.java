package app.utility.parser;

import java.util.Vector;

public interface Parser<E> {
	public Vector<E> extract(String string);
	public String parseToString(Vector<E> list);
}
