DOCKER=docker


run/redis:
	$(DOCKER) run -ti -p 6379:6379 redis:latest
