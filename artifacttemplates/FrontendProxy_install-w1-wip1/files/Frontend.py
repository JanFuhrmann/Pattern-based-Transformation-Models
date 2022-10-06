import pika, uuid

ip = '192.168.178.83'
requestQueueName = 'RequestQueue'
replyQueueName = 'ReplyQueue'

connection = pika.BlockingConnection(pika.ConnectionParameters(host=ip))
channel = connection.channel()

channel.queue_declare(queue=replyQueueName)
def on_reply_received(ch, method, properties, body):
    print(f"Received message: {body}")
channel.basic_consume(queue=replyQueueName, on_message_callback=on_reply_received, auto_ack=True)

message = "Request"
cor_id = str(uuid.uuid4())
print(f"Send message: '{message}' with correlation id {cor_id}")
channel.queue_declare(queue=requestQueueName)
channel.basic_publish(
    exchange='',
    routing_key=requestQueueName,
    properties = pika.BasicProperties(
        reply_to=replyQueueName,
        correlation_id=cor_id),
    body=message)

channel.start_consuming()