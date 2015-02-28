package org.gontard.jfx.svg.factories;

import java.util.Optional;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class ShapeFactory implements Factory {
	private final PaintFactory paintFactory;

	public ShapeFactory(PaintFactory paintFactory) {
		this.paintFactory = paintFactory;
	}

	@Override
	public Shape create(XmlElement el) {
		Shape shape = createShape(el);
		createPaint(el, "fill").ifPresent(shape::setFill);
		createPaint(el, "stroke").ifPresent(shape::setStroke);
		return shape;
	}

	private Optional<Paint> createPaint(XmlElement el, String name) {
		return Optional.ofNullable(paintFactory.create(el.getString(name)));
	}

	protected abstract Shape createShape(XmlElement el);
}
