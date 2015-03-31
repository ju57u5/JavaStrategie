package tk.ju57u5v.engine.components;

import com.sun.org.apache.xpath.internal.operations.Plus;

public class Vec2 {
	public double x,y;
	
	public Vec2(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public Vec2 plus(Vec2 other) {
		return new Vec2(this.x+other.x, this.y+other.y);
	}
	
	public Vec2 minus(Vec2 other) {
		return new Vec2(this.x-other.x, this.y-other.y);
	}
	
	public Vec2 multiply(Vec2 other) {
		return new Vec2(this.x*other.x, this.y*other.y);
	}
	
	public Vec2 multiply(double other) {
		return new Vec2(this.x*other, this.y*other);
	}
	
	public Vec2 div(Vec2 other) {
		return new Vec2(this.x/other.x, this.y/other.y);
	}
	
	public double length() {
		return Math.sqrt(x*x+y*y);
	}
	
	public Vec2 normalize() {
		return new Vec2(this.x/length(), this.y/length());
	}
	
	@Override
	public String toString() {
		return "["+x+","+y+"]";
	}
}