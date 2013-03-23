<%@ page import="com.cardsmart.CardNetwork" %>



<div class="fieldcontain ${hasErrors(bean: cardNetworkInstance, field: 'networkname', 'error')} required">
	<label for="networkname">
		<g:message code="cardNetwork.networkname.label" default="Networkname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="networkname" required="" value="${cardNetworkInstance?.networkname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardNetworkInstance, field: 'cards', 'error')} ">
	<label for="cards">
		<g:message code="cardNetwork.cards.label" default="Cards" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cardNetworkInstance?.cards?}" var="c">
    <li><g:link controller="card" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="card" action="create" params="['cardNetwork.id': cardNetworkInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'card.label', default: 'Card')])}</g:link>
</li>
</ul>

</div>

