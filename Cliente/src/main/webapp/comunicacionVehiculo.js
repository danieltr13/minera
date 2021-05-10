/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const consume = function () {
    var queue = 'task_queue';

// This makes sure the queue is declared before attempting to consume from it
    channel.assertQueue(queue, {
        durable: true
    });

    channel.consume(queue, function (msg) {
        var secs = msg.content.toString().split('.').length - 1;

        console.log(" [x] Received %s", msg.content.toString());
        setTimeout(function () {
            console.log(" [x] Done");
        }, secs * 1000);
    }, {
        // automatic acknowledgment mode,
        // see https://www.rabbitmq.com/confirms.html for details
        noAck: true
    });
};
