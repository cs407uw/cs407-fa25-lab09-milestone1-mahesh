package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if (dT <= 0f) return

        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // Velocity at t1: v1 = v0 + 1/2 * (a1 + a0) * Δt
        val newVx = velocityX + 0.5f * (xAcc + accX) * dT
        val newVy = velocityY + 0.5f * (yAcc + accY) * dT

        // Distance over [t0, t1]:
        // l = v0 * Δt + (Δt^2 / 6) * (3a0 + a1)
        val dt2 = dT * dT
        val dx = velocityX * dT + (dt2 / 6f) * (3f * accX + xAcc)
        val dy = velocityY * dT + (dt2 / 6f) * (3f * accY + yAcc)

        posX += dx
        posY += dy
        velocityX = newVx
        velocityY = newVy

        // Next step's "previous accel"
        accX = xAcc
        accY = yAcc

        checkBoundaries()

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        // Right Boundary
        if (posX > backgroundWidth - ballSize) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }
        // Left Boundary
        if (posX < 0) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }
        // Bottom Boundary
        if (posY > backgroundHeight - ballSize) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }
        // Top Boundary
        if (posY < 0) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)

        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}