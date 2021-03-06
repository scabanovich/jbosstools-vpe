package org.jboss.tools.vpe.editor.util;

import java.lang.reflect.Field;

import org.eclipse.core.runtime.Platform;

public class VpePlatformUtil {
	public static final String LOAD_XULRUNNER_ENGINE = "org.jboss.tools.vpe.engine.xulrunner"; //$NON-NLS-1$
	private static final String SWT_GTK3 = "SWT_GTK3"; //$NON-NLS-1$
	
	public static boolean isGTK3() {
		if (Platform.WS_GTK.equals(Platform.getWS())) {
			try {
				Class<?> clazz = Class.forName("org.eclipse.swt.internal.gtk.OS"); //$NON-NLS-1$
				Field field = clazz.getDeclaredField("GTK3"); //$NON-NLS-1$
				boolean gtk3 = field.getBoolean(field);
				return gtk3;
			} catch (ClassNotFoundException e) {
				return isGTK3Env();
			} catch (NoSuchFieldException e) {
				return false;
			} catch (SecurityException e) {
				return isGTK3Env();
			} catch (IllegalArgumentException e) {
				return isGTK3Env();
			} catch (IllegalAccessException e) {
				return isGTK3Env();
			}
		}
		return false;
	}

	private static boolean isGTK3Env() {
		String gtk3 = System.getProperty(SWT_GTK3);
		if (gtk3 == null) {
			gtk3 = System.getenv(SWT_GTK3);
		}
		return !"0".equals(gtk3); //$NON-NLS-1$
	}
	
	public static boolean isXulrunnerEnabled() {
		return Boolean.valueOf(System.getProperty(LOAD_XULRUNNER_ENGINE));
	}
}
