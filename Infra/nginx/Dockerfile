FROM nginx:alpine

ARG NUM_WORKERS=4

RUN sed -i "s/worker_processes  1;/worker_processes  $NUM_WORKERS;/g" /etc/nginx/nginx.conf