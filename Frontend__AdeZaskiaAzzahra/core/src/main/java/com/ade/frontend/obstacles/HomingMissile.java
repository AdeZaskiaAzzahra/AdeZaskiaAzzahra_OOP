package com.ade.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ade.frontend.Player;

public class HomingMissile extends BaseObstacle {
    private Player target;
   private Vector2 velocity;
   private final float speed = 200f;
   private final float width = 40f;
   private final float height = 20f;

   public HomingMissile(Vector2 startPosition) {
       super(startPosition, 0);
       velocity = new Vector2();
   }

   @Override
   public void initialize(Vector2 startPosition, int length) {
       super.initialize(startPosition, length);
       velocity.set(0, 0);
   }

   public void setTarget(Player target) {
       this.target = target;
   }

   public boolean isTargetingPlayer() {
       if (target == null) return false;

       float missileCenterY = position.y + height / 2f;
       float targetCenterY = target.getPosition().y + target.getHeight() / 2f;

       // Jika posisi tengah target lebih besar dari posisi tengah misil,
       // berarti misil belum melewati target
       return targetCenterY > missileCenterY;
   }

   public void update(float delta) {
       if (!active || target == null) return;

       if (isTargetingPlayer()) {
           Vector2 targetPosition = new Vector2(target.getPosition());
           velocity.set(targetPosition).sub(position).nor().scl(speed);
           position.x += velocity.x * delta;
           position.y += velocity.y * delta;
           updateCollider();
       }
   }

   @Override
   protected void updateCollider() {
       collider = new Rectangle(position.x, position.y, width, height);
   }

   @Override
   protected void drawShape(ShapeRenderer shapeRenderer) {
       shapeRenderer.rect(position.x, position.y, width, height);
   }

   @Override
   public float getRenderWidth() {
       return width;
   }

   @Override
   public float getRenderHeight() {
       return height;
   }
}
