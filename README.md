# Clean code repository testing

Many of the software solutions that are usually developed in the enterprise context has some sort of state that needs to be persited in a durable store for later access. Depending on the specific functional and non-functional requirements, the selection of the right persistance solution can be hard to make, and most likely would require an Architecture Decision Record (ADR) where the rationale of the selection, including alternative and tradeoffs, are detailed. For persisting your application state, most likely you will look at the CAP Theorem to make the right decision.

--> Start with in-memory, but then, what happen with my tests?
--> Explain the classical layered approach, which does nto work since entities are tables
--> Explain the approach of hexagonal architecture, ubitquituous language expressed in the repository as well, riched domain entieies, tell-don't-ask.
--> Show the code for a simple domain, with in-memory repository and then postgres repository.