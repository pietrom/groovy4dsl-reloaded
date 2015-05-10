package rules
class RewardService {
   static boolean on_consume_provided = true
   def static onConsume = {
      on_consume_provided = false
   }
   static boolean on_purchase_provided = true
   def static onPurchase = {
      on_purchase_provided = false
   }
   static boolean on_upgrade_provided = true
   def static onUpgrade = {   
      on_upgrade_provided = false
   }

   void prepareClosures (Binding binding) {
      binding.onConsume = onConsume
      binding.onPurchase = onPurchase
      binding.onUpgrade = onUpgrade
      binding.reward = { spec, closure ->
             closure.delegate = delegate
             binding.result = true
             binding.and = true 
             closure()
      }
      binding.condition = { closure ->
         closure.delegate = delegate

         if (binding.and)
            binding.result = (closure() && binding.result) 
         else
            binding.result = (closure() || binding.result)       
      }

      binding.allOf = { closure ->
         closure.delegate = delegate
         def storeResult = binding.result
         def storeAnd = binding.and
             binding.result = true // Starting premise is true
             binding.and = true 

         closure()
   
         if (storeAnd) {
            binding.result = (storeResult && binding.result)
         } else {
            binding.result = (storeResult || binding.result)
         }
         binding.and = storeAnd
      }

       binding.anyOf = { closure ->
             closure.delegate = delegate
         def storeResult = binding.result
         def storeAnd = binding.and
    
             binding.result = false // Starting premise is false
             binding.and = false 

             closure()    
         if (storeAnd) {
            binding.result = (storeResult && binding.result)
         } else {
            binding.result = (storeResult || binding.result)
         }
         binding.and = storeAnd
      }

      binding.grant = { closure ->
             closure.delegate = delegate
    
             if (binding.result)
                    closure()    
      }
      binding.extend = { days ->
         def bbPlus = new BroadbandPlus()
         bbPlus.extend( binding.account, binding.media, days)
      }
      binding.points = { points ->
         def bbPlus = new BroadbandPlus()
         binding.account.points += points
      }
   }
   void prepareMedia(binding, media) {
      binding.media = media
      binding.isNewRelease = media.newRelease
      binding.isVideo = (media.type == "VIDEO")
      binding.isGame = (media.type == "GAME")
      binding.isSong = (media.type == "SONG")
   }
   static void loadRewardRules() {
      Binding binding = new Binding()
         
      binding.onConsume = onConsume
      binding.onPurchase = onPurchase
      binding.onUpgrade = onUpgrade
         
      GroovyShell shell = new GroovyShell(binding)
	  InputStream inp = RewardService.class.getResourceAsStream("/rewards/rewards.groovy")
	  println inp
      shell.evaluate(new InputStreamReader(inp))
      
      onConsume = binding.onConsume
      onPurchase = binding.onPurchase
      onUpgrade = binding.onUpgrade   
   }
   void applyRewardsOnConsume(account, media) {
      if (on_consume_provided) {
         Binding binding = new Binding()
         binding.account = account
         prepareClosures(binding)
         prepareMedia(binding, media)
         
         GroovyShell shell = new GroovyShell(binding)
         shell.evaluate("onConsume.delegate = this;onConsume()")
      }
   }
   void applyRewardsOnPurchase(account, media) {
      if (on_purchase_provided) {
         Binding binding = new Binding()
         binding.account = account
         prepareClosures(binding)
         prepareMedia(binding, media)
         
         GroovyShell shell = new GroovyShell(binding)
         shell.evaluate("onPurchase.delegate = this;onPurchase()")
      }
   }
   void applyRewardsOnUpgrade(account, plan) {
      if (on_upgrade_provided) {
         Binding binding = new Binding()
         binding.account = account
         binding.toPlan = plan
         binding.from_plan = account.plan
         prepareClosures(binding)
         
         GroovyShell shell = new GroovyShell(binding)
         shell.evaluate("onUpgrade.delegate = this;onUpgrade()")
      }
   }
}