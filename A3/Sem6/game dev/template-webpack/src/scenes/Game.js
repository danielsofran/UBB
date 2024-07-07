import { Scene } from 'phaser';

const speed = 10;
const player_scale = 0.3;
const planetMinScale = 0.2;
const planetMaxScale = 0.5;
const basePlanetHp = 100;
const damage = 5;

export class Game extends Scene
{
    constructor ()
    {
        super('Game');
        this.player1Score = 0;
        this.player2Score = 0;
        this.suspendedCollissions = [];
    }

    preload ()
    {
        this.load.image('background_game', 'assets/background.webp');
        this.load.image('skater1', 'assets/player1.png');
        this.load.image('skater2', 'assets/player2.png');

        const planets = [
            { name: 'slimePlanet', file: 'sheep.png' },
            { name: 'gumPlanet', file: 'pig.png' },
        ];

        planets.forEach(planet => {
            this.load.image(planet.name, `assets/${planet.file}`);
        });

        this.planets = planets.map(planet => planet.name);

        for (let i = 1; i <= 10; i++) {
            this.load.image(`skash_${i.toString().padStart(5, '0')}`, `assets/skash_${i.toString().padStart(5, '0')}.png`);
        }
    }

    create ()
    {
        this.add.image(400, 300, 'background_game');

        this.player1 = this.physics.add.sprite(100, 500, 'skater1')
            .setScale(player_scale)
            .setDepth(20)
            .setCollideWorldBounds(true)
            .setBounce(0.2);
        this.player2 = this.physics.add.sprite(100, 100, 'skater2')
            .setScale(player_scale)
            .setDepth(20)
            .setCollideWorldBounds(true)
            .setBounce(0.2);

        this.initializeInputs();

        this.planetsGroup = this.add.group({
            maxSize: 5
        });

        this.setupPlanetSpawner();

        this.physics.add.collider(this.player1, this.planetsGroup, this.handlePlayerPlanetCollision, null, this);
        this.physics.add.collider(this.player2, this.planetsGroup, this.handlePlayerPlanetCollision, null, this);

        this.player1ScoreText = this.add.text(16, 16, 'Teen farmer score: 0', { fontSize: '32px', fill: '#FFF', fontWeight: 'bold' });
        this.player2ScoreText = this.add.text(584, 16, 'Old farmer score: 0', { fontSize: '32px', fill: '#FFF', fontWeight: 'bold' });

        this.anims.create({
            key: 'skash',
            frames: [
                { key: 'skash_00001' },
                { key: 'skash_00002' },
                { key: 'skash_00003' },
                { key: 'skash_00004' },
                { key: 'skash_00005' },
                { key: 'skash_00006' },
                { key: 'skash_00007' },
                { key: 'skash_00008' },
                { key: 'skash_00009' },
                { key: 'skash_00010' }
            ],
            frameRate: 5,
            repeat: 0
        });

        this.time.addEvent({
            delay: 3000,
            callback: this.spawnAnimatedObject,
            callbackScope: this,
            loop: true
        });
    }

    spawnAnimatedObject() {
        let overlap, x, y;
        do {
            overlap = false;
            x = Phaser.Math.Between(100, 924);
            y = Phaser.Math.Between(100, 668);
            const tempSprite = this.add.sprite(x, y, 'skash_00001');

            [this.player1, this.player2].forEach(player => {
                if (Phaser.Geom.Intersects.RectangleToRectangle(tempSprite.getBounds(), player.getBounds())) {
                    overlap = true;
                }
            });

            this.planetsGroup.getChildren().forEach(planet => {
                if (Phaser.Geom.Intersects.RectangleToRectangle(tempSprite.getBounds(), planet.getBounds())) {
                    overlap = true;
                }
            });

            if (overlap) {
                tempSprite.destroy();
            }
        } while (overlap);

        const animatedSprite = this.physics.add.sprite(x, y, 'skash_00001').play('skash');
        this.time.delayedCall(2000, () => animatedSprite.destroy());
    }


    initializeInputs() {
        this.cursors = this.input.keyboard.createCursorKeys();

        this.wasd = {
            up: this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.W),
            down: this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.S),
            left: this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.A),
            right: this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.D)
        };
    }

    setupPlanetSpawner() {
        this.time.addEvent({
            delay: Phaser.Math.Between(2000, 3000),
            callback: this.spawnPlanet,
            callbackScope: this,
            loop: true
        });
    }

    spawnPlanet() {
        if (this.planetsGroup.countActive(true) < this.planetsGroup.maxSize) {
            let overlap;
            let attempts = 0;
            let planet;
            do {
                overlap = false;
                const x = Phaser.Math.Between(100, 924);
                const y = Phaser.Math.Between(100, 668);
                const scale = Phaser.Math.FloatBetween(planetMinScale, planetMaxScale);
                const roundedScale = Math.round(scale * 10) / 10;
                const planetName = this.planets[Math.floor(Math.random() * this.planets.length)];

                planet = this.physics.add.sprite(x, y, planetName).setScale(roundedScale).setImmovable(true);
                planet.hp = Math.floor(roundedScale * basePlanetHp);
                const radius = planet.body.halfWidth;
                planet.body.setCircle(radius);

                const planetBounds = planet.getBounds();

                this.planetsGroup.getChildren().forEach(existingPlanet => {
                    if (Phaser.Geom.Intersects.RectangleToRectangle(planetBounds, existingPlanet.getBounds())) {
                        overlap = true;
                    }
                });

                [this.player1, this.player2].forEach(player => {
                    if (Phaser.Geom.Intersects.RectangleToRectangle(planetBounds, player.getBounds())) {
                        overlap = true;
                    }
                });

                if (overlap) {
                    planet.destroy();
                } else {
                    planet.hpBar = this.add.graphics();
                    planet.hpText = this.add.text(x, y - 40, `HP: ${planet.hp}`, { fontSize: '20px', fill: '#ff0000', fontWeight: 'bold' }).setOrigin(0.5);
                    this.planetsGroup.add(planet);
                }

                attempts++;
                if (attempts > 10) {
                    return;
                }
            } while (overlap);

            this.planetsGroup.add(planet);
        }
    }

    updateHealthBar(planet) {
        planet.hpBar.clear();
        planet.hpBar.fillStyle(0xff0000, 1);
        planet.hpBar.fillRect(planet.x - planet.body.width / 2, planet.y, planet.body.width * (planet.hp / (planet.scale * basePlanetHp)), 5);
    }

    handlePlayerPlanetCollision(player, planet) {
        const collisionKey = `${player.name}-${planet.name}`;

        if (this.suspendedCollissions.includes(collisionKey) || player.slowText) {
            return;
        }
        this.suspendedCollissions.push(collisionKey);

        switch (planet.texture.key) {
            case 'slimePlanet':
                this.animateBounce(player, planet);
                break;
            case 'gumPlanet':
                this.applySlowEffect(player, 0.5, 2000);
                this.showSlowEffect(player);
                break;
        }

        planet.hp -= damage;
        if (planet.hp <= 0) {
            if (player === this.player1) {
                this.player1Score += planet.scale * 10;
                this.player1ScoreText.setText('Teen farmer score: ' + this.player1Score);
            } else if (player === this.player2) {
                this.player2Score += planet.scale * 10;
                this.player2ScoreText.setText('Old farmer score: ' + this.player2Score);
            }
            planet.hpBar.destroy();
            planet.hpText.destroy();
            planet.destroy();
        } else {
            planet.hpText.setText(`HP: ${planet.hp}`);
            this.updateHealthBar(planet);
        }

        this.time.delayedCall(200, () => {
            this.suspendedCollissions = this.suspendedCollissions.filter(item => item !== collisionKey);
        }, [], this);
    }

    applySlowEffect(player, slowFactor, duration) {
        player.setData('speed', speed * slowFactor);
        this.time.delayedCall(duration, () => {
            player.setData('speed', speed);
        }, [], this);
    }

    showSlowEffect(player) {
        const slowText = this.add.text(player.x, player.y - 70, 'Slowed!', { fontSize: '48px', fill: '#00ff00', fontStyle: 'bold', zIndex: 3000 }).setOrigin(0.5);
        slowText.setAlpha(0.7);
        player.slowText = slowText;

        this.tweens.add({
            targets: slowText,
            alpha: 0,
            duration: 2000,
            ease: 'Linear',
            onComplete: () => {
                slowText.destroy();
                player.slowText = null;
            }
        });
    }


    animateBounce(player, planet) {
        const dx = player.x - planet.x;
        const dy = player.y - planet.y;
        const dist = Math.sqrt(dx * dx + dy * dy);
        const bounceDistance = 100;

        const bounceBackX = player.x + (dx / dist) * bounceDistance;
        const bounceBackY = player.y + (dy / dist) * bounceDistance;

        this.tweens.add({
            targets: player,
            x: bounceBackX,
            y: bounceBackY,
            duration: 100,
            ease: 'Sine.easeInOut',
            repeat: 0,
            onStart: () => {
            },
            onComplete: () => {
            }
        });
    }

    update(time, delta) {
        this.calculatePlayerMovement(this.cursors, this.player1, delta);
        this.calculatePlayerMovement(this.wasd, this.player2, delta)
        this.updatePlayerText(this.player1);
        this.updatePlayerText(this.player2);
    }

    updatePlayerText(player) {
        if (player.slowText) {
            player.slowText.setPosition(player.x, player.y - 70);
        }
    }

    calculatePlayerMovement(controls, player, delta) {
        const currentSpeed = player.getData('speed') || speed;
        player.setVelocity(0);

        let velocityX = 0;
        let velocityY = 0;

        if (controls.left.isDown) {
            velocityX -= currentSpeed;
            player.scaleX = -Math.abs(player.scaleX);
            player.body.offset.x = player.width;
        }
        if (controls.right.isDown) {
            velocityX += currentSpeed;
            player.scaleX = Math.abs(player.scaleX);
            player.body.offset.x = 0;
        }
        if (controls.up.isDown) {
            velocityY -= currentSpeed;
        }
        if (controls.down.isDown) {
            velocityY += currentSpeed;
        }

        if (velocityX !== 0 && velocityY !== 0) {
            const diagSpeed = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
            player.setVelocityX((velocityX / diagSpeed) * currentSpeed * delta);
            player.setVelocityY((velocityY / diagSpeed) * currentSpeed * delta);
        } else {
            player.setVelocityX(velocityX * delta);
            player.setVelocityY(velocityY * delta);
        }
    }
}
