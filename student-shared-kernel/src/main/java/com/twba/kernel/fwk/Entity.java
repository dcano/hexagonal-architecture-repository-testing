package com.twba.kernel.fwk;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by canod on 18/07/2017.
 */
public abstract class Entity extends ModelValidator implements ConcurrencyAware {
    @NotNull
    @Valid
    protected final List<@NotNull Event<? extends DomainEventPayload>> events;
    @PositiveOrZero
    private long version;

    public Entity(long version) {
        this.events = new ArrayList<>();
        this.version = version;
        this.validateProperty("events");
    }

    @Override
    public long getVersion() {
        return version;
    }

    protected void publishEvent(Event<? extends DomainEventPayload> event) {
        event.setAggregateType(aggregateType());
        event.setAggregateId(aggregateId());
        event.setEventStreamVersion(version + events.size());
        this.events.add(event);
    }

    protected void publishEvent(DomainEventPayload event) {
        publishEvent(new Event<>(event));
    }

    public boolean hasEvents(){
        return !events.isEmpty();
    }

    public String aggregateType() {
        return this.getClass().getSimpleName();
    }

    public abstract  String aggregateId();

    /**
     * Retrieve the events
     * @return
     */
    public List<Event<? extends DomainEventPayload>> getDomainEvents() {
        return Collections.unmodifiableList(events);
    }

    protected void setVersion(long version) {
        this.version = version;
        this.validateProperty("version");
    }

    @Override
    public boolean isStaleWith(ConcurrencyAware existingEntity) {
        return Objects.nonNull(existingEntity) && this.getVersion() < existingEntity.getVersion();
    }
}