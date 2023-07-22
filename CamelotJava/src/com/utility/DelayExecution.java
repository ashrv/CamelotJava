package com.utility;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;

public abstract class DelayExecution<T> {
	private ArrayBlockingQueue<T> queue;
	
	protected abstract Event<String> getEvent();
	protected abstract Optional<T> CheckFormat(String msg);

	protected T Wait() throws InterruptedException {
		queue=new ArrayBlockingQueue<>(100);

		var listener= getEvent();
		Consumer<String> consumer = t->{
			try {
				Check(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		listener.Subscribe(consumer);

		var item=queue.take();
		listener.Unsubscribe(consumer);
		return item;
	}
	
	public void Check(String msg) throws InterruptedException {
			var match = CheckFormat(msg);
			if(match.isPresent())
				queue.put(match.get());
		}
	}
