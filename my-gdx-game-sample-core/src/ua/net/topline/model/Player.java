package ua.net.topline.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player {
	public final static float MAX_VELOCITY = 3f;
	public final static float SPEED = 5f;
	public final static float SIZE = 0.8f;

	public Fixture playerPhysicsFixture;
	public Fixture playerSensorFixture;
	public Body box;
	public Vector2 velocity = new Vector2();
	public boolean isJump = false;

	public Player(Body b) {
		box = b;
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(0.4f, 0.4f);
		playerPhysicsFixture = box.createFixture(poly, 0);
		poly.dispose();
		CircleShape circle = new CircleShape();
		circle.setRadius(0.4f);
		circle.setPosition(new Vector2(0, -0.05f));
		playerSensorFixture = box.createFixture(circle, 0);
		circle.dispose();
		box.setBullet(true);

	}

	public float getFriction() {
		return playerSensorFixture.getFriction();
	}

	public Body getBody() {
		return box;
	}

	public void setFriction(float f) {
		playerSensorFixture.setFriction(f);
		playerPhysicsFixture.setFriction(f);
	}

	public Vector2 getPosition() {
		return box.getPosition();
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void update(float delta) {
		Vector2 vel = box.getLinearVelocity();
		velocity.y = vel.y;
		box.setLinearVelocity(velocity);
		if (isJump) {
			box.applyLinearImpulse(0, 14, box.getPosition().x,
					box.getPosition().y);
			isJump = false;
		}
	}

	public void jump() {
		isJump = true;
	}

	public void resetVelocity() {
		getVelocity().x = 0;
		getVelocity().y = 0;
	}
}
