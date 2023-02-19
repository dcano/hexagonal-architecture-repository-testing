# Clean code repository testing

Many of the software solutions that are usually developed in the enterprise context, have some sort of state that needs to be persited in a durable store for later access. Depending on the specific functional and non-functional requirements, the selection of the right persistance solution can be hard to make, and most likely would require an Architecture Decision Record (ADR) where the rationale of the selection, including alternatives and tradeoffs, are detailed. For persisting your application state, most likely you will look at the CAP Theorem to make the most adequate decision.

This decision process should not delay the design and development of your application's domain model. Engineering teams should focus on delivering (business) value, not on maintaining a bunch of DDL scripts and evolving a highly changing database squema, for some weeks (or months) later realize that it would had been better using a document database instead of a relational database.

Also focusing on delivery domain value prevents the team from taking domain related decision based on the constraints of a too-early-taken technical and/or infrastructure related decision (i.e. the database technology in this case). As Uncle Bob said in this [tweet](https://twitter.com/unclebobmartin/status/118403913937453056?ref_src=twsrc%5Etfw%7Ctwcamp%5Etweetembed%7Ctwterm%5E118403913937453056%7Ctwgr%5Ecfbd06a4d7b4371a13ab18348849e58dbf9b481a%7Ctwcon%5Es1_c10&ref_url=https%3A%2F%2Fpublish.twitter.com%2F%3Fquery%3Dhttps3A2F2Ftwitter.com2Funclebobmartin2Fstatus2F118403913937453056widget%3DTweet), the architecture shall allow deferring the framework decisions (and also infrastructure ones)

## Deferring infrastructure decisions

Coming back to the database technology example, a way of dererring the instrastructure decision regadring which database technology shall be used, would be starting with a simple in-memory implementation of your repository where the domain entites can be stored in a list in memory. This approach accelerates the discovery, design and implementation of features and domain use cases, enabling fast feedback cycles with your stakeholders about what really matters: **Domain Value**.

Now you might be thinking: *"but then, I'm not delivering an e2e working feature"* or *"But how I verify the feature with an in-memory adapter of my repository"*. Here, architecture patterns like Hexagonal Architecture (also know as ports and adapters) and methodologies like DDD (not mandatory for having a Clean Architecture and ultimately Clean Code)

Many applications are design following the classical three layered architecture, with Presentation/Controller, Service and Persistance layers. This kind of architecture tend to mix domain definition (e.g. Domain Entites and Value Objects) with tables (e.g. ORM entities). I'm not going to explain here DDD and why I would recommend using it for complex (and not so complex) domains, the article would be too large, but most likely I will create a series of posts talking about it.

The next code snippets might be familiar to you, many times when working with Layered Architecture we say that this is part of our domain, but it is not. Here we are just defining a table, we are designing how the data will be persisted, leveraging frameworks and libraries like Hibernate and Spring, nothing really realted to our domain.

Applying patterns like Hexagonal Architecture, we can define the interactions with external services (like database engines) from the domain perspective as ports (interfaces) which express an set of expectations of the domain, those expectations will be fullfiled by the mean of port implementations (adapters). Is in these adapters where frameworks and libraries will be used.

In the code snippet below you can see how a basic domain Entity and its repository port could look like, together with an inmemory implementation of the port.

This approach will allow to define the tests of my repository port without any dependency on frameworks, and reuse those tests once the team decides what database solutions will be used. Once the domain is better defined, being more stable, the team can move forward to choose the best database technology and create the required adapters, reusing the existing tests to verify the solution.

All the code of this article can be found in this github repository.