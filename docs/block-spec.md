# Crystal Circuitry — Block Specification (MVP)

Namespace: `raven`  
ID Prefix: `CC_`  
Project: Crystal Circuitry

This document defines the **minimum viable block set (MVP)** for Crystal Circuitry.
All blocks listed here are required for the first functional release.

---

## Design Rules (Applies to All Blocks)

- All blocks & items use the `CC_` ID prefix.
- All state changes must be event-driven (no ticking).
- Visual feedback must be subtle and readable.
- Blocks should be understandable without external documentation.
- No item movement or automation logic.

---

## 1. Input Blocks

### 1.1 Green Crystal Switch
**ID:** `CC_GreenSwitch`

**Role:**  
Primary player input. Acts as a binary toggle (on/off).

**States:**
- `off`
- `on`

**Behavior:**
- Player interaction toggles state.
- Emits a signal event when state changes.
- Does nothing while idle.

**Textures Required:**
- `CC_GreenSwitchOff`
- `CC_GreenSwitchOn` (subtle glow)

**Notes:**
- No pulse/timer behavior in MVP.
- This is the only direct player-operated logic block in MVP.

### 1.2 Green Crystal Channels
**ID:** `CC_GC_Channel_[type]`

**Role:**
Allows player to transfer Crystal Power over shorter distances, but only within the shape of the channel provided. 

**States:**
- `off`
- `on`

**Behavior:**
- Player interaction toggles state.
- Emits a signal event when state changes.
- Does nothing while idle.

**Textures Required:**
- `CC_GC_Channel_Straight`
- `CC_GC_Channel_StraightOn`
- `CC_GC_Channel_L`
- `CC_GC_Channel_LOn`
- `CC_GC_Channel_T`
- `CC_GC_Channel_TOn`
- `CC_GC_Channel_Cross`
- `CC_GC_Channel_CrossOn`

**Notes:**
- These are primarily for shorter distances, and may have a range limit similar to a certain other games dust mechanics later on; but current boolean logic does not support this.
 
---

## 2. Signal Transfer Blocks (Targeted)

### 2.1 Yellow Crystal Emitter
**ID:** `CC_YellowEmitter`

**Role:**  
Sends a signal to exactly one paired Yellow Receiver.

**States:**
- `Unlinked`
- `LinkedInactive`
- `LinkedActive`

**Behavior:**
- Can be paired to one `CC_YellowReceiver`.
- When receiving a signal, forwards it to its paired receiver.
- Does not scan for receivers; pairing is explicit.

**Textures Required:**
- `CC_YellowEmitterUnlinked`
- `CC_YellowEmitterLinked`
- `CC_YellowEmitterActive`

---

### 2.2 Yellow Crystal Receiver
**ID:** `CC_YellowReceiver`

**Role:**  
Receives a signal from exactly one paired Yellow Emitter.

**States:**
- `Unlinked`
- `LinkedInactive`
- `LinkedActive`

**Behavior:**
- Reacts only to its paired emitter.
- Forwards signal state to any attached actuator.

**Textures Required:**
- `CC_YellowReceiverUnlinked`
- `CC_YellowReceiverLinked`
- `CC_YellowReceiverActive`

---

## 3. Signal Transfer Blocks (Broadcast / Aura)

### 3.1 White Crystal Emitter
**ID:** `CC_WhiteEmitter`

**Role:**  
Broadcasts a signal to all White Receivers within range.

**States:**
- `Inactive`
- `Active`

**Behavior:**
- Emits signal events to registered receivers within radius.
- Radius is fixed for MVP (configurable later).
- Broadcast occurs only on state change.

**Textures Required:**
- `CC_WhiteEmitterInactive`
- `CC_WhiteEmitterActive`

**Notes:**
- No per-tick radius scanning.
- Receivers register/unregister themselves.

---

### 3.2 White Crystal Receiver
**ID:** `CC_WhiteReceiver`

**Role:**  
Receives broadcast signals from nearby White Emitters.

**States:**
- `Inactive`
- `Active`

**Behavior:**
- Activates when any bound emitter broadcasts `on`.
- Deactivates when all bound emitters are inactive.

**Textures Required:**
- `CC_WhiteReceiverInactive`
- `CC_WhiteReceiverActive`

---

## 4. Actuator Blocks

### 4.1 Crystal Lamp
**ID:** `CC_Lamp`

**Role:**  
Simple visual actuator (light on/off).

**States:**
- `off`
- `on`

**Behavior:**
- Turns on when receiving `on` signal.
- Turns off when receiving `off` signal.
- No light flicker or animation in MVP.

**Textures Required:**
- `CC_LampOff`
- `CC_LampOn` (emissive)

---

### 4.2 Door Anchor Block
**ID:** `CC_DoorAnchor`

**Role:**  
Relays a Crystal signal to one or two nearby door blocks, enabling synchronized opening and closing [ ≤ 5 blocks by default ].

**States:**
- `off` 
- `on`


**Behavior:**
- Toggles state on signal change.
- Only affects linked doors.
- Allows up to 2 doors to be linked to a single anchor.

**Textures Required:**
- `CC_DoorAnchorOff`
- `CC_DoorAnchorOn` (emissive)

**Notes:**
- MVP implementation may swap block state rather than animate movement.
- Advanced movement systems are out of scope.
- The Door Anchor does not store open/closed intentionally - it will store the signal it is given from a switch and relay that to the doors it's linked to.

---

## 5. Explicitly Out of Scope (MVP)

- Timers, delays, inverters
- Item transport or automation
- Moving block physics
- Multi-block machines
- Energy generation or storage

These may be added in later phases only after MVP stability.

---

## MVP Completion Criteria

The MVP is considered complete when:
- All listed blocks can be placed in-game.
- Signals flow correctly between switches, transmitters, and actuators.
- State persists across server restarts.
- No noticeable performance degradation occurs with dozens of blocks active.
