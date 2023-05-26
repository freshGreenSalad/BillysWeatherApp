package com.example.billysweatherapp.features.shortWaveRaditaionFeature.presentation.sunscreen

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.ShaderBrush
import org.intellij.lang.annotations.Language

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun sunShader() {
    shade(WAVE_BAR)
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun RainShader() {
    shade(Rain_BAR)
}
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun tempShader() {
    shade(temp_BAR)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun shade(agsl:String) {
    val time by produceState(0f){
        while (true)
        {
            withInfiniteAnimationFrameMillis {
                value = it /1000f
            }
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .drawWithCache {
            val shader = RuntimeShader(agsl)
            val shaderBrush = ShaderBrush(shader)
            shader.setFloatUniform("iResolution", size.width, size.height)
            onDrawBehind {
                shader.setFloatUniform("iTime", time)
                drawRect(shaderBrush)
            }
        },) {
    }
}

@Language("AGSL")
private const val WAVE_BAR = """
    float bar(vec2 uv, float start, float height){
        return step(uv.y, height + start) - step(uv.y, start);
    }
    
    highp uniform float iTime;
    uniform float2 iResolution;
    
    float4 main (float2 fragCoord){
        vec2 uv = fragCoord / iResolution.xy;
        vec3 col = vec3(1.0, 0.86, 0.50);
        //vec3 col = vec3(0.0, 0.0, 1.0);// red|| yellow to high goes green|| blue pink
        
        for (float i = -0.;i < 1.3; i += 0.1){
            float wave = sin((i*12. + iTime * 1. + uv.x * 5.) * .4) * .08 * (1.1 - i);
            uv.y += wave;
            col += vec3(.3 + i * .40, i * .1, 0.07) * bar(uv, i + .3, i + 13.);
        }
        
        return vec4(col, 1.0);
    }
"""

@Language("AGSL")
private const val Rain_BAR = """
    float bar(vec2 uv, float start, float height){
        return step(uv.y, height + start) - step(uv.y, start);
    }
    
    highp uniform float iTime;
    uniform float2 iResolution;
    
    float4 main (float2 fragCoord){
        vec2 uv = fragCoord / iResolution.xy;
        //vec3 col = vec3(1.0, 0.86, 0.50);
        vec3 col = vec3(0.8, 0.5, 0.62);// red|| yellow to high goes green|| blue pink
        
        for (float i = -0.;i < 1.3; i += 0.1){
            float wave = sin((i*12. + iTime * 1. + uv.x * 5.) * .4) * .08 * (1.1 - i);
            uv.y += wave;
            col += vec3(.3 + i * .40, i * .1, 0.07) * bar(uv, i + .3, i + 13.);
        }
        
        return vec4(col, 1.0);
    }
"""

@Language("AGSL")
private const val temp_BAR = """
    float bar(vec2 uv, float start, float height){
        return step(uv.y, height + start) - step(uv.y, start);
    }
    
    highp uniform float iTime;
    uniform float2 iResolution;
    
    float4 main (float2 fragCoord){
        vec2 uv = fragCoord / iResolution.xy;
        vec3 col = vec3(1.0, 0.56, 0.20);
        //vec3 col = vec3(0.0, 0.0, 1.0);// red|| yellow to high goes green|| blue pink
        
        for (float i = -0.;i < 1.3; i += 0.1){
            float wave = sin((i*12. + iTime * 1. + uv.x * 5.) * .4) * .08 * (1.1 - i); // very flatt |less flat |flatter | | | |
            uv.y += wave;
            col += vec3(.3 + i * .40, i * .1, 0.07) * bar(uv, i + .3, i + 13.);
        }
        
        return vec4(col, 1.0);
    }
"""