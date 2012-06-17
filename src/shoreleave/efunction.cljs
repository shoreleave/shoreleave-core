(ns shoreleave.efunction
  "A Function object abstraction")

;; The Function type is used as a decorator base for functions.
;;
;; This comes in handy if you want create specialty function types
;; (like embedded workers) or want to extend or change the behavior
;; of the base function type.
;;
;; In the [pubsub system](https://github.com/shoreleave/shoreleave-pubsub),
;; the Function type is used to decorate functions that can be published to.
;;
;; These enhanced function types are hashable and support metadata

(deftype Function [f meta]
  
  IWithMeta
  (-with-meta  [F meta]  (Function. f meta))
  
  IMeta
  (-meta [F] meta)

  IFn
  (-invoke [F & args]
    (apply (.-f F) args))
  
  IHash
  (-hash [F] (goog.getUid (.-f F))))

