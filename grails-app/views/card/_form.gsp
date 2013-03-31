<%@ page import="com.cardsmart.Card" %>



<div class="fieldcontain ${hasErrors(bean: cardInstance, field: 'cardname', 'error')} required">
	<label for="cardname">
		<g:message code="card.cardname.label" default="Cardname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cardname" required="" value="${cardInstance?.cardname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardInstance, field: 'issuer', 'error')} required">
	<label for="issuer">
		<g:message code="card.issuer.label" default="Issuer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="issuer" name="issuer.id" from="${com.cardsmart.CardIssuer.list()}" optionKey="id" required="" value="${cardInstance?.issuer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardInstance, field: 'network', 'error')} required">
	<label for="network">
		<g:message code="card.network.label" default="Network" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="network" name="network.id" from="${com.cardsmart.CardNetwork.list()}" optionKey="id" required="" value="${cardInstance?.network?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardInstance, field: 'rewards', 'error')} ">
	<label for="rewards">
		<g:message code="card.rewards.label" default="Rewards" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cardInstance?.rewards?}" var="r">
    <li><g:link controller="cardReward" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="cardReward" action="create" params="['card.id': cardInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'cardReward.label', default: 'CardReward')])}</g:link>
</li>
</ul>

</div>

