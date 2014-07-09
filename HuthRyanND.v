(*
Section nd_rules.

Variables P Q R: Prop.

Axiom and_intro : P -> Q -> (P /\ Q).

Axiom and_elim_l : (P /\ Q) -> P.
Axiom and_elim_r : (P /\ Q) -> Q.

Axiom or_intro_l : P -> (P \/ Q).
Axiom or_intro_r : Q -> (P \/ Q).

Axiom or_elim : (P \/ Q) -> (P -> R) -> (Q -> R) -> R.

Axiom impl_elim : P -> (P -> Q) -> Q.

Axiom not_intro : (P -> False) -> ~P.

Axiom not_elim : P -> ~P -> False.

Axiom false_elim : False -> P.

Axiom not_not_elim : ~(~P) -> P.


End nd_rules.

Section LEM_derivation.

Variable phi : Prop.

Section _1.
  Hypothesis l1 : ~(phi \/ ~phi).
  Section _1_1.
    Hypothesis l2 : phi.
    Fact l3 : phi \/ ~phi. Proof. apply (or_intro_l _ _ l2). Qed.
    Definition l4 := (not_elim _ l3 l1). 
    Fact l4' : False. Proof. apply (not_elim _ l3 l1). Qed.
    Fact l4'' : False. Proof. auto. Qed. Print l4''. Check l1. Print l4'.
  End _1_1.
  Fact l5 : ~phi. Proof. apply not_intro. apply l4. Qed.
  Fact l6 : phi \/ ~phi. Proof. apply or_intro_r. apply l5. Qed.
  Fact l7 : False. Proof. apply not_elim with (P:=phi \/ ~phi). apply l6. apply l1. Qed.
End _1.
Fact l8 : ~~(phi \/ ~phi). Proof. apply not_intro. apply l7. Qed.
Fact LEM : phi \/ ~phi. Proof. apply not_not_elim. apply l8. Qed.

End LEM_derivation.
*)
(*
Section LEM_derivation.

Variable phi : Prop.

Section _1.
  Hypothesis l1 : ~(phi \/ ~phi).
  Section _1_1.
    Hypothesis l2 : phi.
    Fact l3 : phi \/ ~phi. Proof. apply or_intro_l. apply l2. Qed.
    Fact l4 : False. Proof. apply not_elim (*with (P:=phi \/ ~phi)*). apply l3. apply l1. Qed.
  End _1_1.
  Fact l5 : ~phi. Proof. apply not_intro. apply l4. Qed.
  Fact l6 : phi \/ ~phi. Proof. apply or_intro_r. apply l5. Qed.
  Fact l7 : False. Proof. apply not_elim with (P:=phi \/ ~phi). apply l6. apply l1. Qed.
End _1.
Fact l8 : ~~(phi \/ ~phi). Proof. apply not_intro. apply l7. Qed.
Fact LEM : phi \/ ~phi. Proof. apply not_not_elim. apply l8. Qed.

End LEM_derivation.

Check LEM. Print LEM.
*)

Section nd_rules.
Variables P Q R: Prop.
Axiom and_intro : P -> Q -> (P /\ Q).
Axiom and_elim_l : (P /\ Q) -> P.
Axiom and_elim_r : (P /\ Q) -> Q.
Axiom or_intro_l : P -> (P \/ Q).
Axiom or_intro_r : Q -> (P \/ Q).
Axiom or_elim : (P \/ Q) -> (P -> R) -> (Q -> R) -> R.
Axiom impl_elim : P -> (P -> Q) -> Q.
Axiom not_intro : (P -> False) -> ~P.
Axiom not_elim : P -> ~P -> False.
Axiom false_elim : False -> P.
Axiom not_not_elim : ~(~P) -> P.
End nd_rules.
Arguments and_intro [P] [Q] _ _.
Arguments and_elim_l [P] [Q] _.
Arguments and_elim_r [P] [Q] _.
Arguments or_intro_l [P] [Q] _.
Arguments or_intro_r [P] [Q] _.
Arguments or_elim [P] [Q] [R] _ _ _.
Arguments impl_elim [P] [Q] _ _.
Arguments not_intro [P] _ _.
Arguments not_elim [P] _ _.
Arguments false_elim [P] _.
Arguments not_not_elim [P] _.




Parameter phi : Prop.


Section _1.
  Hypothesis l1 : ~(phi \/ ~phi).
  Section _1_1.
    Hypothesis l2 : phi.
    Fact l3 : phi \/ ~phi . Proof. apply (or_intro_l l2). Qed.
    Fact l4 : False. Proof. apply (not_elim l3 l1). Qed.
  End _1_1.
  Fact l5 : ~phi. Proof. apply (not_intro l4). Qed.
  Fact l6 : phi \/ ~phi. Proof. apply (or_intro_r l5). Qed.
  Fact l7 : False. Proof. apply (not_elim l6 l1). Qed.
End _1.
Fact l8 : ~~(phi \/ ~phi). Proof. apply (not_intro l7). Qed.
Fact l9 : phi \/ ~phi. Proof. apply (not_not_elim l8). Qed.



Fact l3 : phi \/ ~phi. Proof. apply (or_intro_l l2). Qed.
