
function sibuncho(cTime, sTime)
{
	var nTime = Math.round((cTime - sTime)/1000.0);
	if(nTime >= 10800)
	{
		nTime = 0;
	}
	var si = 0;
	var bun = 0;
	var cho = 0;
	
	//시
	if(nTime >= 3600) 
	{ 
		console.log('si' + nTime);
		si = Math.floor(nTime / 3600);
		nTime = nTime - si*3600;
	}

	//분
	if (nTime >= 60)
	{
		console.log('bun = ' + nTime);
		bun = Math.floor(nTime / 60);
		nTime = nTime - bun*60
	}
	
	//초
	if(nTime < 60)
	{
		console.log('cho = ' + nTime);
		cho = nTime;
	}
	
	
	if(si > 0)
	{
		return si + ':' + addZero(bun) + ':' + addZero(cho);		
	}
	else
	{
		if(bun > 0)
		{
			return addZero(bun) + ':' + addZero(cho);
		}
		else
		{
			return '00:' + addZero(cho);
		}
	}
	
}

function addZero(num){
	if(num >= 10)
	{
		return num;
	}
	else
	{
		return num = '0' + num;
	}
}



function si(cTime, sTime)
{
	var nTime = Math.round((cTime - sTime)/1000.0);
	var si = 0;
	
	//시
	if(nTime >= 3600) 
	{ 
		si = Math.floor(nTime / 3600);
	}

	return si;
	
}

function bun(cTime, sTime)
{
	var nTime = Math.round((cTime - sTime)/1000.0);
	var bun = 0;
	
	if(nTime >= 3600) 
	{ 
		bun = Math.floor(nTime / 60);
	}

	return bun;
}


function cho(cTime, sTime)
{
	var nTime = Math.round((cTime - sTime)/1000.0);
	return nTime;
}




