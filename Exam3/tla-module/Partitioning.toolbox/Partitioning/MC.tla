---- MODULE MC ----
EXTENDS Partitioning, TLC

\* CONSTANT definitions @modelParameterConstants:0N
const_149498198060093000 == 
3
----

\* CONSTANT definition @modelParameterDefinitions:0
def_ov_149498198060194000 ==
-3..3
----
\* SPECIFICATION definition @modelBehaviorSpec:0
spec_149498198060195000 ==
Spec
----
\* INVARIANT definition @modelCorrectnessInvariants:0
inv_149498198060196000 ==
PartialCorrectness
----
\* PROPERTY definition @modelCorrectnessProperties:0
prop_149498198060197000 ==
Termination
----
=============================================================================
\* Modification History
\* Created Tue May 16 20:46:20 EDT 2017 by Goldacbj
